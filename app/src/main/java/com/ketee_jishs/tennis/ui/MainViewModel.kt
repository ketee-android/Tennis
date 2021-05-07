package com.ketee_jishs.tennis.ui

import android.net.Uri
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ketee_jishs.tennis.retrofit.TennisRetrofitImpl
import com.ketee_jishs.tennis.retrofit.TennisServerResponse
import com.ketee_jishs.tennis.utils.AppState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val tennisRetrofitImpl: TennisRetrofitImpl = TennisRetrofitImpl()
) : ViewModel() {

    val coachName = ObservableField<String>()
    val coachPhoto = ObservableField<Uri>()
    val coachLocation = ObservableField<String>()
    val coachDescription = ObservableField<String>()
    val coachAge = ObservableField<String>()
    val coachGender = ObservableField<String>()
    val coachHeight = ObservableField<String>()
    val coachActiveArm = ObservableField<String>()
    val amountOfFeedbacks = ObservableField<String>()
    val allFeedbacks = ObservableField<String>()
    val allStudents = ObservableField<String>()

    fun getData(): LiveData<AppState> {
        sendServerRequest()
        return liveData
    }

    fun setData(coachPhoto: Uri) {
        this.coachPhoto.set(coachPhoto)
    }

    fun setCoachMainInfo(
        coachName: String,
        coachLocation: String,
        coachDescription: String
    ) {
        this.coachName.set(coachName)
        this.coachLocation.set(coachLocation)
        this.coachDescription.set(coachDescription)
    }

    fun setCoachAdditionalInfo(
        coachAge: String,
        coachGender: String,
        coachHeight: String,
        coachActiveArm: String,
        amountOfFeedbacks: String
    ) {
        this.coachAge.set(coachAge)
        this.coachGender.set(coachGender)
        this.coachHeight.set(coachHeight)
        this.coachActiveArm.set(coachActiveArm)
        this.amountOfFeedbacks.set(amountOfFeedbacks)
    }

    fun setFeedbacksData(allFeedbacks: String) {
        this.allFeedbacks.set("Еще отзывы ($allFeedbacks)")
    }

    fun setStudentsData(allStudents: String) {
        this.allStudents.set("Все ученики ($allStudents)")
    }

    private fun sendServerRequest() {
        tennisRetrofitImpl.getRetrofitImpl().getTennisData().enqueue(object :
            Callback<TennisServerResponse> {
            override fun onResponse(
                call: Call<TennisServerResponse>,
                response: Response<TennisServerResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    liveData.value = AppState.Success(response.body()!!)
                } else {
                    val code = response.code()
                    val message = response.message()
                    if (message.isNullOrEmpty()) {
                        liveData.value = AppState.Error(Throwable("Unidetified error"))
                    } else {
                        liveData.value = AppState.Error(Throwable("$code: $message"))
                    }
                }
            }

            override fun onFailure(call: Call<TennisServerResponse>, t: Throwable) {
                liveData.value = AppState.Error(t)
            }
        })
    }
}