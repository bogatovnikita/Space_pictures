package com.bogatovnikita.spacepictures.viewModel

import com.bogatovnikita.spacepictures.repo.earth_epic.EarthEpicServerResponse
import com.bogatovnikita.spacepictures.repo.pictute_of_the_day.PODServerResponse

sealed class PictureData {
    data class Success(val serverResponse: PODServerResponse, val title: String, val explanation: String, val date:String) : PictureData()
    data class Error(val error: Throwable) : PictureData()
    data class Loading(val process: Int?) : PictureData()

    data class SuccessEarthEpic(val serverResponse: EarthEpicServerResponse) : PictureData()
}