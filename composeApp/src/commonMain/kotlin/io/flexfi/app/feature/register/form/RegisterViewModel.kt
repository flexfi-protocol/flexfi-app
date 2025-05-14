package io.flexfi.app.feature.register.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.flexfi.app.domain.session.SessionRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val repository: SessionRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvents = Channel<UiEvent>()
    val uiEvents = _uiEvents.receiveAsFlow()

    fun onFirstNameChange(firstName: String) {
        _uiState.update { it.copy(firstName = firstName) }
    }

    fun onLastNameChange(lastName: String) {
        _uiState.update { it.copy(lastName = lastName) }
    }

    fun onEmailChange(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        _uiState.update { it.copy(confirmPassword = confirmPassword) }
    }

    fun onReferralCodeChange(referralCode: String) {
        _uiState.update { it.copy(referralCode = referralCode) }
    }

    fun onGdprAcceptedChange() {
        _uiState.update { it.copy(gdprAccepted = !it.gdprAccepted) }
    }

    fun onPrivacyPolicyAcceptedChange() {
        _uiState.update { it.copy(privacyPolicyAccepted = !it.privacyPolicyAccepted) }
    }

    fun onRegisterButtonClick() {
        _uiState.update { it.copy(loading = true) }

        val state = uiState.value

        checkForm()
        if (!isFormValid()) {
            _uiState.update { it.copy(loading = false) }
            return
        }

        viewModelScope.launch {
            repository.register(
                email = state.email,
                password = state.password,
                firstName = state.firstName,
                lastName = state.lastName,
                referralCodeUsed = state.referralCode.ifBlank { null },
            ).onSuccess {
                println("Registration successful: $it")
                _uiEvents.sendEvent(UiEvent.StartCongratulationUi)
            }.onFailure {
                println("Registration failed: ${it.message}")
                _uiEvents.sendEvent(
                    UiEvent.ShowSnackBar(
                        message = "An error occurred. Please retry later"
                    )
                )
            }
            _uiState.update { it.copy(loading = false) }
        }
    }

    private fun checkForm() {
        val state = uiState.value

        _uiState.update {
            it.copy(
                firstNameError = if (state.firstName.isBlank()) {
                    "First name cannot be empty"
                } else {
                    null
                },
                lastNameError = if (state.firstName.isBlank()) {
                    "First name cannot be empty"
                } else {
                    null
                },
                emailError = if (state.email.isBlank()) {
                    "Email cannot be empty"
                } else {
                    null
                },
                passwordError = when {
                    state.password.isBlank() -> "Password cannot be empty"
                    !isPasswordValid(state.password) -> "Password must be at least 12 characters long and include uppercase, lowercase, a number, and a special character"
                    else -> null
                },
                confirmPasswordError = when {
                    state.confirmPassword.isBlank() -> "Password cannot be empty"
                    !isPasswordValid(state.password) -> "Password must be at least 12 characters long and include uppercase, lowercase, a number, and a special character"
                    state.password != state.confirmPassword -> "Password do not match"
                    else -> null
                }
            )
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).{12,}$".toRegex().matches(password)
    }

    private fun isFormValid(): Boolean {
        val state = uiState.value
        return state.firstNameError == null &&
                state.lastNameError == null &&
                state.emailError == null &&
                state.passwordError == null &&
                state.confirmPasswordError == null &&
                state.privacyPolicyAccepted &&
                state.gdprAccepted
    }

    private fun <T> Channel<T>.sendEvent(event: T) {
        viewModelScope.launch { send(event) }
    }
}
