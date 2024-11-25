package com.example.waytogo.Retrofit

import android.util.Log
import com.example.waytogo.BuildConfig.SEARCH_API_BASE
import com.example.waytogo.utils.Constants.TAG
import com.example.waytogo.utils.isJsonArray
import com.example.waytogo.utils.isJsonObject
import com.google.gson.JsonParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule{

    @Singleton
    @Provides
    fun provideOkHttpClients(): OkHttpClient{
        val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger{
            override fun log(message: String) {
                Log.d(TAG, "log: ${message}")
                when{
                    message.isJsonObject() ->
                        Log.d(TAG, JsonParser.parseString(message).asJsonObject.toString())
                    message.isJsonArray() ->
                        Log.d(TAG, JsonParser.parseString(message).asJsonObject.toString())
                    else -> {
                        try {
                            Log.d(TAG, JsonParser.parseString(message).asJsonObject.toString())
                        } catch (e: Exception){
                            Log.d(TAG, "LoggingInterCept : ${message}")
                        }
                    }
                }
            }

        })
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    @Named("SearchRegion")
    fun provideRetrofitSearchRegion(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create()) // Gson converter 추가
            .client(okHttpClient)
            .baseUrl(SEARCH_API_BASE)
            .build()
    }

    @Singleton
    @Provides
    @Named("SearchService")
    fun provideApiServiceSearch(
        @Named("SearchRegion") retrofit: Retrofit
    ): SearchService {
        return retrofit.create(SearchService::class.java)
    }
}