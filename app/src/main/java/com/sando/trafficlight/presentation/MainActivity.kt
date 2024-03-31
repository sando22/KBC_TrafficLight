package com.sando.trafficlight.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sando.trafficlight.presentation.main.MainScreen
import com.sando.trafficlight.presentation.trafficlight.TrafficLightScreen
import com.sando.trafficlight.ui.theme.TrafficLightTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrafficLightTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController, startDestination = "main") {
                        composable("main") { MainScreen(navController = navController) }
                        composable("trafficLight/{modelName}") { TrafficLightScreen() }
                    }
                }
            }
        }
    }
}