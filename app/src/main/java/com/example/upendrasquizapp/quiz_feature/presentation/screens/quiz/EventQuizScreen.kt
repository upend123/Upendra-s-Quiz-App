package com.example.upendrasquizapp.quiz_feature.presentation.screens.quiz

sealed class EventQuizScreen {
    data class GetQuizzesByApi(val  noOfQuizzes: Int,val category: Int, val difficulty: String,val type: String) : EventQuizScreen()
    object GetQuizzesByDB: EventQuizScreen()
    data class SetOptionSelected(val quizStateIndex: Int ,val selectedOptionIndex : Int): EventQuizScreen()
    object CalculateFinalScore: EventQuizScreen()
}
