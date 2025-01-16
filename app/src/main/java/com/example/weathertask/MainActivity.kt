package com.example.weathertask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.core.Screens
import com.example.current_weather.CurrentWeatherScreen
import com.example.search_cities.ui.SearchCitiesScreen
import com.example.weathertask.ui.theme.WeatherTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            navController = rememberNavController()
            WeatherTaskTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        NavigationGraph(navController)
                    }
                }
            }
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {

    NavHost(navController, startDestination = Screens.SearchCities.screenRoute) {
        composable(Screens.SearchCities.screenRoute) {
            SearchCitiesScreen(navController)
        }

        composable(
            route = Screens.CurrentWeather.screenRoute,
            arguments = listOf(navArgument("city") { type = NavType.StringType })
        ) { backStackEntry ->
            CurrentWeatherScreen(navController, backStackEntry.arguments?.getString("city") ?: "")
        }

    }
}

