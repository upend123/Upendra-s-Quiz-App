package com.example.upendrasquizapp.quiz_feature.domain.usecases.quizUseCase.quiz

import com.example.upendrasquizapp.quiz_feature.data.local.db.QuizDatabaseRepo

class DeleteQuizBYIdUseCase(
    private val quizDatabaseRepo: QuizDatabaseRepo
) {
    suspend operator fun invoke(id: Int) {
        quizDatabaseRepo.deleteQuizById(id)
    }

}