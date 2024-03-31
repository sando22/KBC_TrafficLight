package com.sando.trafficlight.presentation.trafficlight

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.android.parcel.Parcelize

class TrafficLightViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    val screenState = savedStateHandle.getStateFlow(SAVED_STATE_KEY, TrafficLightState())

    init {
        savedStateHandle[SAVED_STATE_KEY] = screenState.value.copy(
            modelName = checkNotNull(savedStateHandle["modelName"])
        )
    }

    companion object {
        private const val SAVED_STATE_KEY = "SAVED_STATE_KEY"
    }
}

@Parcelize
data class TrafficLightState(
    val modelName: String = "",
    val activeLight: TrafficLightColor = TrafficLightColor.ORANGE
) : Parcelable

enum class TrafficLightColor {
    RED,
    ORANGE,
    GREEN
}