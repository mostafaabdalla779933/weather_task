package com.example.forecast

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.core.convertMillisToDate
import com.example.core.getIconUrl
import com.example.core.showToast
import com.example.domain.entity.ForecastDayData
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ForecastScreen(
    navHostController: NavHostController,
    city: String,
    viewModel: ForecastVM = hiltViewModel()
) {

    val context = LocalContext.current

    val forecastState by viewModel.forecastState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.intent.emit(ForecastIntent.FetchForecast(city))
    }

    when (forecastState) {
        is ForecastState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }

        }

        is ForecastState.Success -> {
            LazyColumn(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize()
            ) {
                items((forecastState as ForecastState.Success).data) { forecast ->
                    DayItem(forecast)
                }
            }
        }

        is ForecastState.Error -> {
            context.showToast((forecastState as ForecastState.Error).message)
        }
    }

}

@Composable
fun DayItem(forecastDayData: ForecastDayData) {

    Row(
        modifier = Modifier
            .padding(5.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF0080F9))
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(15.dp)
    ) {
        GlideImage(
            modifier = Modifier
                .background(Color.Transparent)
                .align(Alignment.CenterVertically)
                .width(50.dp)
                .height(50.dp),
            imageModel = { getIconUrl(forecastDayData.icon) },
            imageOptions = ImageOptions(contentScale = ContentScale.Fit),
            loading = {
                CircularProgressIndicator()
            }
        )

        Column {

            Text(
                fontSize = 15.sp,
                color = Color.White,
                text = forecastDayData.date.convertMillisToDate()
            )

            Text(
                fontSize = 15.sp,
                color = Color.White,
                text = "description: ${forecastDayData.description}"
            )
            Text(
                fontSize = 15.sp,
                color = Color.White,
                text = "temperature: ${forecastDayData.temp}"
            )
            Text(
                fontSize = 15.sp,
                color = Color.White,
                text = "min temperature: ${forecastDayData.temp_min}"
            )
            Text(
                fontSize = 15.sp,
                color = Color.White,
                text = "max temperature: ${forecastDayData.temp_max}"
            )

        }
    }
}

@Preview
@Composable
fun DayItemPreview() {
    DayItem(
        ForecastDayData(
            icon = "",
            description = "description",
            id = 0,
            date = 0,
            temp = 0.0,
            temp_min = 0.0,
            temp_max = 0.0
        )
    )
}


