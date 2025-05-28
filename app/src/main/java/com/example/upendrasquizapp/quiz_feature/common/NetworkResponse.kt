package com.example.upendrasquizapp.quiz_feature.common

sealed class NetworkResponse<out T>{

    data class Success<out T>(val data :T): NetworkResponse<T>()
    data class Error(val massage: String): NetworkResponse<Nothing>()
    object Loading: NetworkResponse<Nothing>()

}