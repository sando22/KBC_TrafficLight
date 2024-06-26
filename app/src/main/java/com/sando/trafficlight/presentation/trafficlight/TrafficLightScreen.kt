package com.sando.trafficlight.presentation.trafficlight

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sando.trafficlight.domain.TrafficLightColor
import com.sando.trafficlight.ui.theme.GreenOff
import com.sando.trafficlight.ui.theme.GreenOn
import com.sando.trafficlight.ui.theme.OrangeOff
import com.sando.trafficlight.ui.theme.OrangeOn
import com.sando.trafficlight.ui.theme.RedOff
import com.sando.trafficlight.ui.theme.RedOn

@Composable
fun TrafficLightScreen(
    viewModel: TrafficLightViewModel = viewModel()
) {
    val state = viewModel.screenState.collectAsState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = state.value.modelName, style = MaterialTheme.typography.displayLarge)

        Spacer(modifier = Modifier.fillMaxHeight(0.15f))

        LightCircle(
            activeColor = RedOn,
            inactiveColor = RedOff,
            activated = state.value.activeLight == TrafficLightColor.RED
        )

        Spacer(modifier = Modifier.height(4.dp))

        LightCircle(
            activeColor = OrangeOn,
            inactiveColor = OrangeOff,
            activated = state.value.activeLight == TrafficLightColor.ORANGE
        )

        Spacer(modifier = Modifier.height(4.dp))

        LightCircle(
            activeColor = GreenOn,
            inactiveColor = GreenOff,
            activated = state.value.activeLight == TrafficLightColor.GREEN
        )
    }
}

@Composable
fun LightCircle(
    activeColor: Color,
    inactiveColor: Color,
    activated: Boolean
) {
    val animatedColor by animateColorAsState(
        targetValue = if (activated) {
            activeColor
        } else {
            inactiveColor
        },
        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing),
        label = "backgroundColorAnimation"
    )

    Box(
        modifier = Modifier
            .height(100.dp)
            .aspectRatio(1f)
            .clip(CircleShape)
            .drawBehind {
                drawCircle(animatedColor)
            })
}