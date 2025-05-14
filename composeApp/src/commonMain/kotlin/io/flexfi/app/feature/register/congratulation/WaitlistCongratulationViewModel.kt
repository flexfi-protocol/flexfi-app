package io.flexfi.app.feature.register.congratulation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.flexfi.app.domain.session.SessionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WaitlistCongratulationViewModel(
    private val repository: SessionRepository,
) : ViewModel() {


    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.userPreferences.collect { userPreferences ->
                _uiState.update {
                    it.copy(
                        name = userPreferences.name,
                        referralCode = userPreferences.referralCode,
                        positionInWaitlist = userPreferences.positionInWaitlist, //TODO: retrieve via api call
                    )
                }
            }
        }
    }
}
