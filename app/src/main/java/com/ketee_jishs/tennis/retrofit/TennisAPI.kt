package com.ketee_jishs.tennis.retrofit

import com.ketee_jishs.tennis.utils.DYNAMIC_URL
import retrofit2.Call
import retrofit2.http.GET

interface TennisAPI {
    @GET(DYNAMIC_URL)
    fun getTennisData(): Call<TennisServerResponse>
}