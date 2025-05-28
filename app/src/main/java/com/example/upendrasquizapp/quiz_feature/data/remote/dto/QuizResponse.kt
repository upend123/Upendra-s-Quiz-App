package com.example.upendrasquizapp.quiz_feature.data.remote.dto

data class QuizResponse(
    val response_code: Int,
    val results: List<QuizDto>
)