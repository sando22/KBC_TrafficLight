package com.sando.trafficlight.domain

sealed class TrafficLightModel(
    val color: TrafficLightColor,
    val duration: Long
) {
    class RED(
        color: TrafficLightColor = TrafficLightColor.RED,
        duration: Long = 4000
    ) : TrafficLightModel(color, duration)

    class ORANGE(
        color: TrafficLightColor = TrafficLightColor.ORANGE,
        duration: Long = 1000
    ) : TrafficLightModel(color, duration)

    class GREEN(
        color: TrafficLightColor = TrafficLightColor.GREEN,
        duration: Long = 4000
    ) : TrafficLightModel(color, duration)
}

enum class TrafficLightColor {
    RED,
    ORANGE,
    GREEN
}