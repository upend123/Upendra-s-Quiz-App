package com.example.upendrasquizapp.quiz_feature.data.local.db

import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import com.example.upendrasquizapp.quiz_feature.data.local.entity.QuizEntity
import com.example.upendrasquizapp.quiz_feature.presentation.screens.quiz.QuizState

data class  StateQuizDatabase(
    var loading: Boolean = false,
    var dataOfQuizEntity: List<QuizEntity> = emptyList<QuizEntity>(),
    var onError: String = "",
    //for showing state
    var id : Int = 0


)