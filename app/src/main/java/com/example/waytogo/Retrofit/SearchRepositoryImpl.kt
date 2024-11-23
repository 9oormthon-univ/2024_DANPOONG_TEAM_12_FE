package com.example.waytogo.Retrofit

import com.google.gson.Gson
import com.google.gson.JsonElement
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class SearchRepositoryImpl @Inject constructor(
    @Named("SearchService") private val searchService: SearchService
) : SearchRepository {

    override suspend fun requestSearch(query: String): SearchResponse {
        // Retrofit 호출
        val response: Response<JsonElement> = searchService.getSearch(query = query)

        // 응답이 성공적일 때
        if (response.isSuccessful) {
            response.body()?.let { jsonElement ->
                // JsonElement를 SearchResponse로 변환
                return parseSearchResponse(jsonElement)
            }
        }

        // 실패한 경우 예외 처리
        throw Exception("Failed to fetch search results")
    }

    // JsonElement를 SearchResponse로 변환하는 함수
    private fun parseSearchResponse(jsonElement: JsonElement): SearchResponse {
        // Gson을 사용하여 JsonElement를 SearchResponse 객체로 변환
        val gson = Gson()
        return gson.fromJson(jsonElement, SearchResponse::class.java)
    }
}