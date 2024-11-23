package com.example.waytogo.utils

import com.example.waytogo.BuildConfig

object Constants {
    const val TAG : String = "로그"
}

object API {
    const val NATIVE_KEY : String = BuildConfig.NATIVE_KEY
}

enum class RESPONSE_STATE {
    SUCCESS,
    FAIL
}

