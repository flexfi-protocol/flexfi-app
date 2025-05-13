package io.flexfi.app.feature.register.congratulation

import androidx.lifecycle.ViewModel
import io.flexfi.app.domain.session.SessionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class WaitlistCongratulationViewModel(
    private val repository: SessionRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

}
