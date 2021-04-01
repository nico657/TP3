package ca.ulaval.ima.tp3.Networking

import android.security.identity.AccessControlProfileId
import ca.ulaval.ima.tp3.BuildConfig
import ca.ulaval.ima.tp3.model.Brand
import ca.ulaval.ima.tp3.model.BrandList
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET

interface TP3API {
    companion object{
        const val BASE_URL:String = "tp3.infomobile.app/api/v1/"
    }
    @GET(BASE_URL+ "brand/")
    fun listBrand(): Call<ContentResponse<BrandList<Brand>>>

    data class ContentResponse<T> (
        @SerializedName("content") val content : T,
        @SerializedName("meta") val meta: Meta,
        @SerializedName("error") var error: Error
    )

    data class Error (
        @SerializedName("display") val displayMessage: String
    )

    data class Meta (
        @SerializedName("status_code") val statusCode: Int
    )

}