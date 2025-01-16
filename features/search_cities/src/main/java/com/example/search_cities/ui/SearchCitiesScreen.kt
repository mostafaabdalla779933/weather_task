package com.example.search_cities.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.core.Screens
import com.example.core.getCityName
import com.example.core.showToast


@Composable
fun SearchCitiesScreen(
    navHostController: NavHostController,
    viewModel: SearchCitiesVM = hiltViewModel()
) {
    var query by remember { mutableStateOf(TextFieldValue()) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TextField(
            modifier = Modifier
                .background(Color.White)
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            value = query,
            onValueChange = { newQuery ->
                query = newQuery
                if (newQuery.text.isNotEmpty()) {
                    viewModel.searchPlaces(query.text)
                } else {
                    viewModel.clearSuggestions()
                }
            },
            maxLines = 1,
            label = { Text(text = "Search for a place", color = Color.DarkGray) }
        )
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxHeight(.5f)
        ) {
            Card(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(paddingValues = PaddingValues(horizontal = 16.dp))

                ) {
                    items(viewModel.suggestionsState) { suggestion: String ->
                        CityItem(suggestion) {
                            query = query.copy(
                                text = suggestion,
                                selection = TextRange(suggestion.length)
                            )
                            suggestion.getCityName(context)?.let { city ->
                                viewModel.clearSuggestions()
                                navHostController.navigate(
                                    Screens.CurrentWeather.createRoute(
                                        city.replace(" ","")
                                    )
                                )
                            } ?: run {
                                context.showToast("check connection")
                            }

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CityItem(suggestion: String, onClick: () -> Unit) {
    Text(
        text = suggestion,
        color = Color.Black,
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(12.dp)
            .clickable(onClick = onClick)
    )
}

