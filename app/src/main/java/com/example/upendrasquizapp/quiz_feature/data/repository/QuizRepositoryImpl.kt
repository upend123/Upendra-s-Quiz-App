package com.example.upendrasquizapp.quiz_feature.data.repository

import com.example.upendrasquizapp.quiz_feature.data.mapper.toDomainQuiz
import com.example.upendrasquizapp.quiz_feature.data.remote.api.QuizApi
import com.example.upendrasquizapp.quiz_feature.domain.model.Quiz
import com.example.upendrasquizapp.quiz_feature.domain.repository.QuizRepository

class QuizRepositoryImpl(
    private val api: QuizApi
): QuizRepository {
    override suspend fun geQuizzes(
        amount: Int,
        category: Int,
        difficulty: String,
        type: String
    ): List<Quiz> {
     return api.getQuizzes(amount = amount, category = category,difficulty = difficulty,type = type).results.map {
         it.toDomainQuiz()
     }
    }

}