package com.sando.trafficlight.presentation.trafficlight

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sando.trafficlight.domain.TrafficLightColor
import com.sando.trafficlight.domain.TrafficLightUseCase
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.launch

class TrafficLightViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val trafficLightUseCase = TrafficLightUseCase()

    val screenState = savedStateHandle.getStateFlow(SAVED_STATE_KEY, TrafficLightState())

    init {
        savedStateHandle[SAVED_STATE_KEY] = screenState.value.copy(
            modelName = checkNotNull(savedStateHandle["modelName"])
        )

        viewModelScope.launch {
            trafficLightUseCase.trafficLightFlow.collect { activeLight ->
                savedStateHandle[SAVED_STATE_KEY] = screenState.value.copy(
                    activeLight = activeLight.color
                )
            }
        }

        viewModelScope.launch {
            trafficLightUseCase.startTrafficLight()
        }
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