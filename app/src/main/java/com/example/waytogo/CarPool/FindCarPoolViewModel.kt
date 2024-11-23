package com.example.waytogo.CarPool

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.waytogo.Retrofit.SearchDocumentsResponse
import com.example.waytogo.Retrofit.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FindCarPoolViewModel @Inject constructor(
    private val searchRepository: SearchRepository
): ViewModel() {

    private val _searchPositions = MutableLiveData<List<SearchDocumentsResponse>>()
    val searchPositions: LiveData<List<SearchDocumentsResponse>> = _searchPositions

    // Event Wrapper, SingleLiveData, Flow SharedFlow
    private val _toast = MutableLiveData<String>()
    val toast: LiveData<String> = _toast

    fun search(text: String) {

        viewModelScope.launch {
            runCatching { searchRepository.requestSearch(text) }
                .onSuccess {
                    val documents = it.documents
                    _searchPositions.value = documents
                }
                .onFailure {
                    _toast.value = "Api 호출 실패"
                }
        }
    }
}