package com.bogatovnikita.spacepictures.viewModel

import com.bogatovnikita.spacepictures.repo.PDOServerResponse

sealed class PictureData {
    data class Success(val serverResponse: PDOServerResponse) : PictureData()
    data class Error(val error: Throwable) : PictureData()
    data class Loading(val process: Int?) : PictureData()
}