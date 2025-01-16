package com.example.current_weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.core.Screens
import com.example.core.getIconUrl
import com.example.core.showToast
import com.example.domain.entity.WeatherEntity
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage


@Composable
fun CurrentWeatherScreen(
    navHostController: NavHostController,
    city: String,
    viewModel: CurrentWeatherVM = hiltViewModel()
) {

    val loading by viewModel.loadingState.collectAsState()
    val weather by viewModel.weatherState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.fetchWeather(city)
    }

    LaunchedEffect(Unit) {
        viewModel.error.collect {
            context.showToast(it)
        }
    }

    if (loading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        weather?.let { WeatherDetails(navHostController,city,it) }
    }
}


@Composable
fun WeatherDetails(navHostController: NavHostController,city: String,weather: WeatherEntity) {
    Column(
        modifier = Modifier
            .background(Color(0xFF0080F9))
            .padding(20.dp)
            .fillMaxSize()

    ) {
        GlideImage(
            modifier = Modifier
                .background(Color.Transparent)
                .align(Alignment.CenterHorizontally)
                .width(150.dp)
                .height(150.dp),
            imageModel = { getIconUrl(weather.icon) },
            imageOptions = ImageOptions(contentScale = ContentScale.Fit),
            loading = {
                CircularProgressIndicator()
            }
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 22.sp,
            color = Color.White,
            text = weather.name
        )

        Text(fontSize = 22.sp, color = Color.White, text = "description: ${weather.desc}")
        Text(fontSize = 22.sp, color = Color.White, text = "temperature: ${weather.temp}")
        Text(fontSize = 22.sp, color = Color.White, text = "min temperature: ${weather.temp_min}")
        Text(fontSize = 22.sp, color = Color.White, text = "max temperature: ${weather.temp_max}")

        Button(
            modifier = Modifier
                .padding(vertical = 15.dp)
                .align(Alignment.CenterHorizontally), onClick = {
                navHostController.navigate(
                    Screens.ForecastWeather.createRoute(
                        city
                    )
                )

            }, colors = ButtonColors(
                containerColor = Color(0xFFFF4475),
                contentColor = Color.White,
                disabledContainerColor = Color(0xFFFF4475),
                disabledContentColor = Color.White,
            )
        ) {
            Text(text = "show forecast")
        }
    }
}