package com.example.waytogo.Retrofit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {

    private val _regionSearch = MutableStateFlow<SearchResponse?>(null)
    val regionSearch: StateFlow<SearchResponse?> = _regionSearch

    fun getRegionSearch(query: String) = viewModelScope.launch {
        val response = searchRepository.requestSearch(query)
        _regionSearch.emit(response)
    }
}