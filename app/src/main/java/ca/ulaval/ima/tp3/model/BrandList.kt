package ca.ulaval.ima.tp3.model

import ca.ulaval.ima.tp3.Networking.TP3API
import com.google.gson.annotations.SerializedName

data class   BrandList<T> (
    @SerializedName("content") val results: List<T>,
    @SerializedName("meta") val meta: TP3API.Meta
)
