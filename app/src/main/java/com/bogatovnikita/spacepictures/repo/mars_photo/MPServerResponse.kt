package com.bogatovnikita.spacepictures.repo.mars_photo

import com.google.gson.annotations.SerializedName

data class MPServerResponse(
    @field:SerializedName("img_src") val imgSrc: String?,
    @field:SerializedName("earth_date") val earth_date: String?)
