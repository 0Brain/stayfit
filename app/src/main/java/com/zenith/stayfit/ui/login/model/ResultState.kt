package com.zenith.stayfit.ui.login.model


sealed class ResultState {
    class Success(val items: List<String>) : ResultState()
    class Failure(val errorMessage: String) : ResultState()
    object Cancelled : ResultState()
}