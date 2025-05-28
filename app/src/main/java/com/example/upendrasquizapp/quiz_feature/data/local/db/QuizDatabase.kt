package com.example.upendrasquizapp.quiz_feature.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.upendrasquizapp.quiz_feature.data.local.dao.QuizDao
import com.example.upendrasquizapp.quiz_feature.data.local.entity.QuizEntity

@Database(entities = [QuizEntity::class], version = 1, exportSchema = false)
abstract class QuizDatabase: RoomDatabase() {
    abstract fun quizDao(): QuizDao
}