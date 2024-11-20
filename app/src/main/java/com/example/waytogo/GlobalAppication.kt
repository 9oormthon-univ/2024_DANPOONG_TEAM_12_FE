package com.example.waytogo

import android.app.Application
import com.example.waytogo.utils.API.NATIVE_KEY
import com.kakao.sdk.common.KakaoSdk

class GlobalAppication : Application() {

    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, "${NATIVE_KEY}")
    }
}