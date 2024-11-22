package com.example.waytogo.Retrofit

import android.util.Log
import com.example.waytogo.CarPool.CarPoolModal.CarpoolModalFragment
import com.example.waytogo.CarPool.CarPoolModal.CarpoolModalFragment.Companion
import com.example.waytogo.utils.Constants.TAG
import com.google.gson.JsonElement
import retrofit2.Response

class RetrofitManager {

    companion object {
        val instance = RetrofitManager()
    }

    private val iRetrofit: SearchService? = RetrofitModule.provideRetrofitSearchRegion(RetrofitModule.provideOkHttpClients())?.create(SearchService::class.java)

    // RetrofitManager에서 네트워크 호출 시 예외 처리
    suspend fun searchMaps(searchTerm: String?): Response<JsonElement>? {
        val term = searchTerm ?: ""
        return try {
            val response = iRetrofit?.getSearch(query = term)


            response // Response 객체 반환
        } catch (e: Exception) {
            Log.e(TAG, "API 호출 실패: ${e.message}")
            null
        }
    }
}