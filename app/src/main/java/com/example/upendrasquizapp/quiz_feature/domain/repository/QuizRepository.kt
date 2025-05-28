package com.example.upendrasquizapp.quiz_feature.domain.repository

import com.example.upendrasquizapp.quiz_feature.domain.model.Quiz


interface QuizRepository {

    suspend fun geQuizzes(amount: Int,category: Int,difficulty: String,type: String): List<Quiz>

}