package io.flexfi.app.libraries.result

import androidx.annotation.CheckResult
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.flexfi.app.libraries.result.ResultOf.Failure
import io.flexfi.app.libraries.result.ResultOf.Success
import kotlin.coroutines.cancellation.CancellationException

sealed class ResultOf<out S, out F> {
    abstract val value: Any?

    data class Success<out S>(override val value: S) : ResultOf<S, Nothing>()
    data class Failure<out F>(override val value: F) : ResultOf<Nothing, F>()

    val isSuccess: Boolean get() = this is Success<*>
    val isFailure: Boolean get() = this is Failure<*>

    companion object {

        @CheckResult
        inline operator fun <reified S> invoke(block: () -> S): ResultOf<S, Throwable> = try {
            Success(block())
        } catch (e: Throwable) {
            Failure(e)
        }

        @CheckResult
        inline fun <reified S> success(value: S): Success<S> = Success(value)

        @CheckResult
        inline fun <reified F> failure(value: F): Failure<F> = Failure(value)

    }
}

@CheckResult
fun <S, F> ResultOf<S, F>.successOrNull(): S? = when (this) {
    is Success -> value
    is Failure -> null
}

@CheckResult
fun <S, F> ResultOf<S, F>.successOrElse(onFailure: (failure: F) -> S): S = when (this) {
    is Success -> value
    is Failure -> onFailure(value)
}

@CheckResult
fun <S, F> ResultOf<S, F>.successOrDefault(default: S): S = when (this) {
    is Success -> value
    is Failure -> default
}

fun <S, F> ResultOf<S, F>.successOrThrow(
    throwable: (F) -> Throwable = { IllegalStateException() },
): S = when (this) {
    is Success -> value
    is Failure -> throw throwable(value)
}

@CheckResult
fun <S, F> ResultOf<S, F>.failureOrNull(): F? = when (this) {
    is Success -> null
    is Failure -> value
}

fun <S, F> ResultOf<S, F>.failureOrThrow(
    throwable: (S) -> Throwable = { IllegalStateException() },
): F = when (this) {
    is Success -> throw throwable(value)
    is Failure -> value
}

@CheckResult
inline fun <S1, S2, F1, F2> ResultOf<S1, F1>.map(
    onSuccess: (S1) -> S2,
    onFailure: (F1) -> F2,
): ResultOf<S2, F2> = when (this) {
    is Success -> mapSuccess(onSuccess)
    is Failure -> mapFailure(onFailure)
}

@CheckResult
inline fun <S1, reified S2> ResultOf<S1, Nothing>.mapCatching(
    transform: (S1) -> S2,
): ResultOf<S2, Throwable> = when (this) {
    is Success -> ResultOf { transform(value) }
    is Failure -> this
}

@CheckResult
inline fun <S1, reified S2, F> ResultOf<S1, F>.mapCatching(
    catching: (Throwable) -> F,
    transform: (S1) -> S2,
): ResultOf<S2, F> = when (this) {
    is Success -> ResultOf { transform(value) }.mapFailure(catching)
    is Failure -> this
}

@CheckResult
inline fun <S1, reified S2, F> ResultOf<S1, F>.mapCatching(
    failure: F,
    transform: (S1) -> S2,
): ResultOf<S2, F> = mapCatching(
    catching = { failure },
    transform = transform,
)

@CheckResult
inline fun <S1, S2, F1> ResultOf<S1, F1>.fold(
    onSuccess: (S1) -> S2,
    onFailure: (F1) -> S2,
): S2 = when (this) {
    is Success -> onSuccess(value)
    is Failure -> onFailure(value)
}

@CheckResult(suggest = "#onSuccess(transform)")
inline fun <S1, S2, F> ResultOf<S1, F>.mapSuccess(
    transform: (S1) -> S2,
): ResultOf<S2, F> = when (this) {
    is Success -> Success(transform(value))
    is Failure -> this
}

@CheckResult
inline fun <S1, S2, F> ResultOf<S1, F>.flatMapSuccess(
    map: (S1) -> ResultOf<S2, F>,
): ResultOf<S2, F> = when (this) {
    is Success -> map(value)
    is Failure -> this
}

@CheckResult(suggest = "#onFailure(transform)")
inline fun <S, F1, F2> ResultOf<S, F1>.mapFailure(
    transform: (F1) -> F2,
): ResultOf<S, F2> = when (this) {
    is Success -> this
    is Failure -> Failure(transform(value))
}

@CheckResult
inline fun <S, F> ResultOf<S, F>.recover(
    transform: (F) -> S,
): Success<S> = when (this) {
    is Success -> this
    is Failure -> Success(transform(value))
}

@CheckResult
inline fun <S, F> ResultOf<S, F>.flatRecover(
    transform: (F) -> ResultOf<S, F>,
): ResultOf<S, F> = when (this) {
    is Success -> this
    is Failure -> transform(value)
}

@CheckResult
inline fun <reified S, F> ResultOf<S, F>.recoverCatching(
    transform: (F) -> S,
): ResultOf<S, Throwable> = when (this) {
    is Success -> this
    is Failure -> ResultOf { transform(value) }
}

@CheckResult
inline fun <reified S, F> ResultOf<S, F>.recoverCatching(
    catching: (Throwable) -> S,
    transform: (F) -> S,
): Success<S> = when (this) {
    is Success -> this
    is Failure -> ResultOf { transform(value) }.recover(catching)
}

@CheckResult
inline fun <reified S, F> ResultOf<S, F>.recoverCatching(
    success: S,
    transform: (F) -> S,
): Success<S> = recoverCatching(
    catching = { success },
    transform = transform,
)

inline fun <S, F> ResultOf<S, F>.onSuccess(
    action: (S) -> Unit,
): ResultOf<S, F> = apply {
    when (this) {
        is Success -> action(value)
        is Failure -> Unit
    }
}

inline fun <S, F> ResultOf<S, F>.onFailure(
    action: (F) -> Unit,
): ResultOf<S, F> = apply {
    when (this) {
        is Success -> Unit
        is Failure -> action(value)
    }
}

inline fun <S, reified F : Throwable> ResultOf<S, F>.throwIf(
    predicate: (F) -> Boolean,
): ResultOf<S, F> = onFailure {
    if (predicate(it)) throw it
}

inline fun <S, reified F : Throwable> ResultOf<S, F>.throwUnless(
    predicate: (F) -> Boolean,
): ResultOf<S, F> = onFailure {
    if (!predicate(it)) throw it
}

suspend inline fun <reified S> apiCall(call: () -> HttpResponse): ResultOf<S, Error> {
    return ResultOf(call)
        .mapSuccess { it.body<S>() }
        .throwIfCancelled()
        .mapFailure { Error(it) }
}


inline fun <S, reified F : Throwable> ResultOf<S, F>.throwIfCancelled(): ResultOf<S, F> =
    throwIf { it is CancellationException }


