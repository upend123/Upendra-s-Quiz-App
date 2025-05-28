package com.example.upendrasquizapp.quiz_feature.domain.usecases.quizUseCase.quiz

import com.example.upendrasquizapp.quiz_feature.data.local.entity.QuizEntity
import com.example.upendrasquizapp.quiz_feature.data.local.db.QuizDatabaseRepo

class DeleteQuizUseCase(
    private val quizDatabaseRepo: QuizDatabaseRepo
) {

    suspend operator fun invoke(quizEntity: QuizEntity) {
        quizDatabaseRepo.deleteQuiz(quizEntity)
    }
}
