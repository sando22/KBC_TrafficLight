package com.sando.trafficlight.presentation.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sando.trafficlight.R

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainScreenViewModel = viewModel()
) {
    val state = viewModel.screenState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = state.value.modelName,
            onValueChange = {
                viewModel.onEvent(
                    MainScreenEvent.InputFieldChange(it)
                )
            },
            label = {
                Text(
                    text = if (state.value.modelError) {
                        stringResource(R.string.model_name_length_hint)
                    } else {
                        stringResource(R.string.what_is_your_can_model)
                    }
                )
            },
            isError = state.value.modelError
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (viewModel.checkModelNameValid()) {
                navController.navigate("trafficLight/${state.value.modelName}")
            }
        }) {
            Text(text = stringResource(R.string.start))
        }
    }
}