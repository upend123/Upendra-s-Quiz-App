package com.example.upendrasquizapp.quiz_feature.presentation.screens.quiz

import com.example.upendrasquizapp.quiz_feature.domain.model.Quiz

data class StateQuizScreen(
    var loading: Boolean = false,
    var quizState: List<QuizState> = emptyList<QuizState>(),
    var error: String = "",
    var score : Int = 0
)

data class QuizState(
    val quiz : Quiz ? = null,
    val shuffledOptions: List<String> = emptyList(),
    val selectedOptions: Int? = -1
)