package com.example.upendrasquizapp.quiz_feature.data.remote.dto

data class QuizDto(
    val category: String,
    val correct_answer: String,
    val difficulty: String,
    val incorrect_answers: List<String>,
    val question: String,
    val type: String
)