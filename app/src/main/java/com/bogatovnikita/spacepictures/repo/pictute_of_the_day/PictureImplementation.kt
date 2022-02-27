package com.bogatovnikita.spacepictures.repo.pictute_of_the_day

import com.bogatovnikita.spacepictures.view.MyApp.Companion.retrofit
import com.google.gson.GsonBuilder
import retrofit2.converter.gson.GsonConverterFactory

class PictureImplementation {
    private val baseURL = "https://api.nasa.gov/"

    fun getRetrofitImp(): PictureAPI {
        val podRetrofitImpl = retrofit
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
        return podRetrofitImpl.create(PictureAPI::class.java)
    }
}