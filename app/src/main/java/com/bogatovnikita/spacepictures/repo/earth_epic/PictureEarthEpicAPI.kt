package com.bogatovnikita.spacepictures.repo.earth_epic

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureEarthEpicAPI {
    @GET("/planetary/earth/assets")
    fun getPicturesEarthEpic(@Query("api_key") apiKey: String): Call<EarthEpicServerResponse>
}