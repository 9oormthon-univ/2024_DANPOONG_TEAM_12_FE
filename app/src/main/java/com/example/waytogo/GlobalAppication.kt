package com.example.waytogo

import android.app.Application
import com.example.waytogo.utils.API.NATIVE_KEY
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltAndroidApp
class GlobalAppication : Application() {

    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, "${NATIVE_KEY}")
    }
}