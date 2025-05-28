package com.example.upendrasquizapp.quiz_feature.domain.model

data class Quiz(
    val category: String,
    val type: String,
    val difficulty: String,
    val question: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>
)