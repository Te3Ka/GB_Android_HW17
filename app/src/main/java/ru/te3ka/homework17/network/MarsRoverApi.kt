package ru.te3ka.homework17.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ru.te3ka.homework17.model.Photo
import ru.te3ka.homework17.model.PhotoResponse

interface MarsRoverApi {

    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    suspend fun getPhotos(
        @Query("sol") sol: Int,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): PhotoResponse

    companion object {
        private const val BASE_URL = "https://api.nasa.gov/"

        fun create(): MarsRoverApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(MarsRoverApi::class.java)
        }
    }
}