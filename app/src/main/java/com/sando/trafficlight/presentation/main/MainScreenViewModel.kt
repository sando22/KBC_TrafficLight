package com.sando.trafficlight.presentation.main

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.android.parcel.Parcelize

class MainScreenViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    val screenState = savedStateHandle.getStateFlow(SAVED_STATE_KEY, MainScreenState())

    fun onEvent(event: MainScreenEvent) {
        when (event) {
            is MainScreenEvent.InputFieldChange -> {
                savedStateHandle[SAVED_STATE_KEY] =
                    screenState.value.let { state ->
                        state.copy(
                            modelName = event.newText,
                            modelError = if (state.modelError) {
                                event.newText.length < MINIMAL_MODEL_NAME_LENGTH
                            } else {
                                state.modelError
                            }
                        )
                    }
            }
        }
    }

    fun checkModelNameValid(): Boolean =
        if (screenState.value.modelName.length < MINIMAL_MODEL_NAME_LENGTH) {
            savedStateHandle[SAVED_STATE_KEY] = screenState.value.copy(modelError = true)

            false
        } else {
            true
        }

    companion object {
        private const val MINIMAL_MODEL_NAME_LENGTH = 3

        private const val SAVED_STATE_KEY = "SAVED_STATE_KEY"
    }
}

@Parcelize
data class MainScreenState(
    val modelName: String = "",
    val modelError: Boolean = false
) : Parcelable

sealed class MainScreenEvent {
    data class InputFieldChange(val newText: String) : MainScreenEvent()
}