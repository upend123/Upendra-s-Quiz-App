package com.example.upendrasquizapp.quiz_feature.data.local.db

import com.example.upendrasquizapp.quiz_feature.data.local.dao.QuizDao
import com.example.upendrasquizapp.quiz_feature.data.local.entity.QuizEntity
import kotlinx.coroutines.flow.onEach


class QuizDatabaseRepo(private val dao: QuizDao){

    suspend fun upsertQuiz(quizEntity: QuizEntity){
        dao.upsertQuiz(quizEntity)
    }
    suspend fun deleteQuiz(quizEntity: QuizEntity){
        dao.deleteQuiz(quizEntity)
    }
    fun getAllQuizzes() = dao.getAllQuizzes().onEach { quizEntity ->  }

    suspend fun deleteQuizById(id: Int){
        dao.deleteQuizById(id)
    }

    suspend fun getQuizEntityById(id:Int) = dao.getQuizEntityById(id)

}