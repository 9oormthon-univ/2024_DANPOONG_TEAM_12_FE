package com.example.waytogo.CarPool.CarPoolApplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CarPoolViewModel : ViewModel() {
    val isApplySelected = MutableLiveData<Boolean>()
    val isWriteSelected = MutableLiveData<Boolean>()
    val applyTitle = MutableLiveData<String>()
}
