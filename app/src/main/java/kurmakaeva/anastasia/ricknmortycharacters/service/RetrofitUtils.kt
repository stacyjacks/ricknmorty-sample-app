package kurmakaeva.anastasia.ricknmortycharacters.service

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://rickandmortyapi.com/api/"

val retrofit: Retrofit by lazy {
    val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    val client = OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .build()

    Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
}