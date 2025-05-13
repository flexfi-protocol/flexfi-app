package io.flexfi.app.feature.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.flexfi.app.domain.session.SessionRepository
import io.flexfi.app.feature.register.state.UiState
import io.flexfi.app.libraries.result.onFailure
import io.flexfi.app.libraries.result.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val repository: SessionRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

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
        val state = uiState.value

        viewModelScope.launch {
            repository.register(
                email = state.email,
                password = state.password,
                firstName = state.firstName,
                lastName = state.lastName,
                referralCodeUsed = state.referralCode.ifBlank { null },
            ).onSuccess {
                println("Registration successful: $it")
            }.onFailure {
                throw it.throwable
            }
        }
    }
}
