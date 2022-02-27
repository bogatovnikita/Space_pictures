package com.bogatovnikita.spacepictures.repo.moon_photo

import com.google.gson.annotations.SerializedName

data class MPServerResponse(
    @field:SerializedName("url") val url: String?,
    @field:SerializedName("date") val date: String?
)
