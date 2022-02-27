package com.bogatovnikita.spacepictures.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bogatovnikita.spacepictures.BuildConfig
import com.bogatovnikita.spacepictures.R
import com.bogatovnikita.spacepictures.repo.pictute_of_the_day.PODServerResponse
import com.bogatovnikita.spacepictures.repo.pictute_of_the_day.PictureImplementation
import com.bogatovnikita.spacepictures.utils.LOG_E
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class PODViewModel(
    private val liveData: MutableLiveData<PictureData> = MutableLiveData(),
    private val pictureImplementation: PictureImplementation = PictureImplementation()
) : ViewModel() {
    fun getData(): LiveData<PictureData> {
        return liveData
    }


    fun sendRequest() {
        liveData.postValue(PictureData.Loading(null))
        pictureImplementation.getRetrofitImp().getPictureOfTheDay(BuildConfig.NASA_API_KEY).enqueue(
            object : Callback<PODServerResponse> {
                override fun onResponse(
                    call: Call<PODServerResponse>,
                    response: Response<PODServerResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        response.body()?.let {
                            liveData.postValue(PictureData.Success(it, it.title,it.explanation))
                        }
                    } else {
                        when (response.code()) {
                            400 -> Log.e(LOG_E, R.string.bad_request.toString())
                            401 -> Log.e(LOG_E, R.string.unauthorized.toString())
                            402 -> Log.e(LOG_E, R.string.payment_required.toString())
                            403 -> Log.e(LOG_E, R.string.forbidden.toString())
                            404 -> Log.e(LOG_E, R.string.not_found.toString())
                        }
                    }
                }

                override fun onFailure(call: Call<PODServerResponse>, t: Throwable) {
                    throw IOException(R.string.IOE_exception.toString())
                }
            }
        )
    }
}