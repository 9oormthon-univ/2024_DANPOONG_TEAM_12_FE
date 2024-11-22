package com.example.waytogo.Retrofit

import com.example.waytogo.BuildConfig.REST_API_KEY
import com.google.gson.JsonElement
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SearchService {
    @GET("v2/local/search/address")
    @Headers("Authorization: KakaoAK ${REST_API_KEY}")
    suspend fun getSearch(@Query("query") query: String): Response<JsonElement>
}