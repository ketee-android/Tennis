package com.ketee_jishs.tennis.utils

import com.ketee_jishs.tennis.retrofit.TennisServerResponse

sealed class AppState {
    data class Success(val tennisServerResponse: TennisServerResponse) : AppState()
    data class Error (val error: Throwable) : AppState()
}