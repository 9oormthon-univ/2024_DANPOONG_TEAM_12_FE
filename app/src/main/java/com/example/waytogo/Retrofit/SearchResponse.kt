package com.example.waytogo.Retrofit

import com.google.gson.annotations.SerializedName

data class SearchResponse (
    @SerializedName("meta") val searchMeta: SearchMetaResponse,
    @SerializedName("documents") val documents: List<SearchDocumentsResponse>
)

data class SearchMetaResponse(
    @SerializedName("is_end") val isEnd : Boolean,
    @SerializedName("pageable_count") val pageableCount : Int,
    @SerializedName("total_count") val totalCount : Int
)

data class SearchDocumentsResponse(
//    @SerializedName("address") val address : Address?,
//    @SerializedName("address_name") val addressName : String,
//    @SerializedName("address_type") val addressType : String,
    @SerializedName("x") val x: Double,    // x=128.601805491072
    @SerializedName("y") val y: Double,    // y=35.8713802646197
)

data class Address(
    @SerializedName("address_name") val addressName : String?,
    @SerializedName("region_1depth_name") val regionDepthName1 : String?,
    @SerializedName("region_2depth_name") val regionDepthName2 : String?,
    @SerializedName("region_3depth_h_name") val regionDepthHName3 : String?,
    @SerializedName("region_3depth_name") val regionDepthName3 : String?,
    @SerializedName("x") val x : Double?,
    @SerializedName("y") val y : Double?,
)