package com.bogatovnikita.spacepictures.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bogatovnikita.spacepictures.BuildConfig
import com.bogatovnikita.spacepictures.R
import com.bogatovnikita.spacepictures.repo.earth_epic.EarthEpicImplementation
import com.bogatovnikita.spacepictures.repo.earth_epic.EarthEpicServerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class EarthEpicViewModel(
    private val liveData: MutableLiveData<PictureData> = MutableLiveData(),
    private val earthEpicImplementation:
    EarthEpicImplementation = EarthEpicImplementation()
) : ViewModel() {
    fun getData(): LiveData<PictureData> {
        return liveData
    }

    fun sendRequest() {
        liveData.postValue(PictureData.Loading(null))
        earthEpicImplementation.getRetrofitImp().getPicturesEarthEpic(BuildConfig.NASA_API_KEY)
            .enqueue(
                object : Callback<EarthEpicServerResponse> {
                    override fun onResponse(
                        call: Call<EarthEpicServerResponse>,
                        response: Response<EarthEpicServerResponse>
                    ) {
                        response.body()?.let {
                            liveData.postValue(PictureData.SuccessEarthEpic(it))
                        }
                    }

                    override fun onFailure(call: Call<EarthEpicServerResponse>, t: Throwable) {
                        throw IOException(R.string.IOE_exception.toString())
                    }
                }
            )
    }
}