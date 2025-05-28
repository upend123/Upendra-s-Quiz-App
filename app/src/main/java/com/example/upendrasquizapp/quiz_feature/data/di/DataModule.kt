package com.example.upendrasquizapp.quiz_feature.data.di


import com.example.upendrasquizapp.quiz_feature.data.remote.api.QuizApi
import com.example.upendrasquizapp.quiz_feature.data.repository.QuizRepositoryImpl
import com.example.upendrasquizapp.quiz_feature.domain.repository.QuizRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {


@Singleton
 @Provides
   fun provideQuizRepository(quizApi: QuizApi): QuizRepository{
        return QuizRepositoryImpl(quizApi)
    }

}