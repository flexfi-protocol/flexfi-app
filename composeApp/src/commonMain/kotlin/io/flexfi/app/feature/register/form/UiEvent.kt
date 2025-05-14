package io.flexfi.app.feature.register.form

sealed class UiEvent {
    object StartCongratulationUi : UiEvent()
    data class ShowSnackBar(val message: String) : UiEvent()
}
