package com.bogatovnikita.spacepictures.repo.pictute_of_the_day

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureAPI {
    @GET("planetary/apod")
    fun getPictureOfTheDay(@Query("api_key") apiKey: String): Call<PODServerResponse>
}