package com.sando.trafficlight.presentation.trafficlight

import androidx.compose.foundation.background
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sando.trafficlight.ui.theme.GreenOn
import com.sando.trafficlight.ui.theme.OrangeOff
import com.sando.trafficlight.ui.theme.RedOff

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
        Text(text = state.value.modelName)

        Spacer(modifier = Modifier.fillMaxHeight(0.15f))

        LightCircle(color = RedOff, turnedOn = true)

        Spacer(modifier = Modifier.height(4.dp))

        LightCircle(color = OrangeOff, turnedOn = true)

        Spacer(modifier = Modifier.height(4.dp))

        LightCircle(color = GreenOn, turnedOn = true)
    }
}

@Composable
fun LightCircle(
    color: Color,
    turnedOn: Boolean
) {
    Box(
        modifier = Modifier
            .height(50.dp)
            .aspectRatio(1f)
            .clip(CircleShape)
            .background(color)
    )
}