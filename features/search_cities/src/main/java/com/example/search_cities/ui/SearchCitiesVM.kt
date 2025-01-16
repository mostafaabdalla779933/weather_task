package com.example.search_cities.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.Resource
import com.example.domain.usecase.GetWeatherUseCase
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchCitiesVM @Inject constructor(
    private val placesClient: PlacesClient
) : ViewModel() {

    var suggestionsState: SnapshotStateList<String> = mutableStateListOf()


    fun searchPlaces(query: String) {
        val request = FindAutocompletePredictionsRequest.builder()
            .setSessionToken(AutocompleteSessionToken.newInstance())
            .setQuery(query)
            .build()
        placesClient.findAutocompletePredictions(request)
            .addOnSuccessListener { response ->
                suggestionsState = response.autocompletePredictions.map {
                    it.getFullText(null).toString()
                }.toMutableStateList()
            }
    }



    fun clearSuggestions() {
        suggestionsState.clear()
    }


}