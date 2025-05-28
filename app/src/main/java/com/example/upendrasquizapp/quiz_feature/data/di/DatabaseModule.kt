package com.example.upendrasquizapp.quiz_feature.data.di

import android.app.Application
import androidx.room.Room
import com.example.upendrasquizapp.quiz_feature.data.local.db.QuizDatabase
import com.example.upendrasquizapp.quiz_feature.data.local.db.QuizDatabaseRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideQuizDatabase(application: Application): QuizDatabase{
        return Room.databaseBuilder(application, QuizDatabase::class.java,"quiz_database.sql"
            ).build()
    }
    @Provides
    @Singleton
    fun provideQuizDatabaseRepo(database: QuizDatabase): QuizDatabaseRepo{
        return QuizDatabaseRepo(database.quizDao())
    }


}