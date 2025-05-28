package com.example.upendrasquizapp.quiz_feature.presentation.screens.home

data class StateHomeScreen(
    var numberOfQuizzes: Int = 10,
    var category: String = "General Knowledge",
    var difficulty : String = "Easy",
    var type : String  = "Multiple Choice"
)