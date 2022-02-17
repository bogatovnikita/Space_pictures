package com.bogatovnikita.spacepictures.repo

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureAPI {
    @GET("planetary/apod")
    fun getPictureOfTheDay(@Query("api_key") apiKey: String): Call<PDOServerResponse>
}