package ardents.workmanagementsystem.Netwok

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL="https://ssapi.marmelos.co.in/"
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiServices:ApiServices= getRetrofit().create(ApiServices::class.java)

}