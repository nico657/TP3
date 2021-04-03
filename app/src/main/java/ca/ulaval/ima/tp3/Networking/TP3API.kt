package ca.ulaval.ima.tp3.Networking

import android.security.identity.AccessControlProfileId
import ca.ulaval.ima.tp3.BuildConfig
import ca.ulaval.ima.tp3.model.Brand
import ca.ulaval.ima.tp3.model.BrandList
import ca.ulaval.ima.tp3.model.LightOutput
import ca.ulaval.ima.tp3.model.Model
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TP3API {
    companion object{
        const val API_V1 = "/api/v1/"
        const val BASE_URL:String = "https://tp3.infomobile.app"
    }
    @GET(API_V1 + "brand/")
    fun listBrand(): Call<ContentResponse<List<Brand>>>

    @GET (API_V1 + "brand/{id}/models/")
    fun listModel(@Path("id") brand_id:Int?): Call<ContentResponse<List<Model>>>

    @GET (API_V1 + "model/")
    fun listBrandModel(): Call<ContentResponse<List<Model>>>

    @GET(API_V1 + "offer/search/")
    fun offerDetail(
        @Query("model") modelID: Int?,
        @Query("brand") brandID: Int?) : Call<ContentResponse<LightOutput>>


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