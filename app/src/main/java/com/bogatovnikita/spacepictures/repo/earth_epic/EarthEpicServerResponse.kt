package com.bogatovnikita.spacepictures.repo.earth_epic

import com.google.gson.annotations.SerializedName

data class EarthEpicServerResponse(
    @field:SerializedName("url") val url: String?,
    @field:SerializedName("date") val date: String?,
)
