package com.bogatovnikita.spacepictures.repo.earth_epic

import com.bogatovnikita.spacepictures.view.MyApp
import com.google.gson.GsonBuilder
import retrofit2.converter.gson.GsonConverterFactory

class EarthEpicImplementation {
    private val baseURL = "https://api.nasa.gov/"

    fun getRetrofitImp(): PictureEarthEpicAPI {
        val podRetrofitImpl = MyApp.retrofit
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
        return podRetrofitImpl.create(PictureEarthEpicAPI::class.java)
    }
}