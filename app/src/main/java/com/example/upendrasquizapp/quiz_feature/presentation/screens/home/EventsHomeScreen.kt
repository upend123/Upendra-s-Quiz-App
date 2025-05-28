package com.example.upendrasquizapp.quiz_feature.presentation.screens.home

sealed class EventsHomeScreen {

    data class SetNumberOfQuizzes(val numberOfQuizzes: Int) : EventsHomeScreen()
    data class SetCategory(val category: String) : EventsHomeScreen()
    data class SetDifficulty(val difficulty: String):  EventsHomeScreen()
    data class SetType(val type: String): EventsHomeScreen()

}