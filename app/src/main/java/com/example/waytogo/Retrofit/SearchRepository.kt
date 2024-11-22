package com.example.waytogo.Retrofit

interface SearchRepository {
    suspend fun requestSearch(query: String) : SearchResponse
}