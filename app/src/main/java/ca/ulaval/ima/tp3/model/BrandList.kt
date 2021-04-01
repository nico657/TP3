package ca.ulaval.ima.tp3.model

import ca.ulaval.ima.tp3.Networking.TP3API
import com.google.gson.annotations.SerializedName

data class BrandList<T> (
    @SerializedName("count") val totalCount: Int,
    @SerializedName("next") val nextPage: Int,
    @SerializedName("previous") val previousPage: Int,
    @SerializedName("results") val results: List<T>,
    @SerializedName("meta") val meta: TP3API.Meta
)
