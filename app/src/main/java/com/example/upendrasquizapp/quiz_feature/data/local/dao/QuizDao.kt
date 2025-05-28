package com.example.upendrasquizapp.quiz_feature.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.upendrasquizapp.quiz_feature.data.local.entity.QuizEntity

import kotlinx.coroutines.flow.Flow


@Dao
interface QuizDao {

    @Upsert
    suspend fun upsertQuiz(quizEntity: QuizEntity)

    @Delete
    suspend fun deleteQuiz(quizEntity: QuizEntity)

    @Query("DELETE FROM `quiz_entity` WHERE id = :id")
    suspend fun deleteQuizById(id: Int)

    @Query("SELECT * FROM `quiz_entity` WHERE id = :id")
    suspend fun getQuizEntityById(id: Int): QuizEntity?


    @Query("SELECT * FROM `quiz_entity` ORDER BY `createdAt` DESC")
   fun getAllQuizzes(): Flow<List<QuizEntity>>

}