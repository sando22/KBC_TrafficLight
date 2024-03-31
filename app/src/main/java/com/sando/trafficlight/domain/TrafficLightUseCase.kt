package com.sando.trafficlight.domain

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TrafficLightUseCase {
    private val _trafficLightFlow: MutableStateFlow<TrafficLightModel> =
        MutableStateFlow(TrafficLightModel.RED())
    val trafficLightFlow = _trafficLightFlow.asStateFlow()

    suspend fun startTrafficLight() {
        while (true) {
            when (trafficLightFlow.value) {
                is TrafficLightModel.GREEN -> {
                    _trafficLightFlow.value = TrafficLightModel.ORANGE()
                }

                is TrafficLightModel.ORANGE -> {
                    _trafficLightFlow.value = TrafficLightModel.RED()
                }

                is TrafficLightModel.RED -> {
                    _trafficLightFlow.value = TrafficLightModel.GREEN()
                }
            }

            delay(trafficLightFlow.value.duration)
        }
    }
}