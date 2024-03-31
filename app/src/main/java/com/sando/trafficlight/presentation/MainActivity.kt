package com.sando.trafficlight.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sando.trafficlight.R
import com.sando.trafficlight.ui.theme.TrafficLightTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MainScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrafficLightTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainScreenViewModel
) {
    val state = viewModel.screenState.collectAsState()

    Column(
        modifier = modifier.fillMaxSize(),
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
            viewModel.onEvent(MainScreenEvent.StartDriving)
        }) {
            Text(text = stringResource(R.string.start))
        }
    }
}