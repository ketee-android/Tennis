package com.ketee_jishs.tennis.retrofit

import com.google.gson.GsonBuilder
import com.ketee_jishs.tennis.utils.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TennisRetrofitImpl {
    fun getRetrofitImpl(): TennisAPI {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
        return retrofit.create(TennisAPI::class.java)
    }
}