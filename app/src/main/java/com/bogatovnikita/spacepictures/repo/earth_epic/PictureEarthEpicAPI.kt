package com.bogatovnikita.spacepictures.repo.earth_epic

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureEarthEpicAPI {
    @GET("EPIC/api/natural")
    fun getPicturesEarthEpic(@Query("api_key") apiKey: String): Call<EarthEpicServerResponse>
}