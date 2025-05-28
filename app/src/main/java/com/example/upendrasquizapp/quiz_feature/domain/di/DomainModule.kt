package com.example.upendrasquizapp.quiz_feature.domain.di

import com.example.upendrasquizapp.quiz_feature.data.local.db.QuizDatabaseRepo
import com.example.upendrasquizapp.quiz_feature.domain.repository.QuizRepository
import com.example.upendrasquizapp.quiz_feature.domain.usecases.quizUseCase.quiz.DeleteQuizBYIdUseCase
import com.example.upendrasquizapp.quiz_feature.domain.usecases.quizUseCase.quiz.DeleteQuizUseCase
import com.example.upendrasquizapp.quiz_feature.domain.usecases.quizUseCase.quiz.GetQuizzesByDBUseCase
import com.example.upendrasquizapp.quiz_feature.domain.usecases.quizUseCase.quiz.GetQuizzesByNetworkUseCase
import com.example.upendrasquizapp.quiz_feature.domain.usecases.quizUseCase.quiz.UpsertQuizUseCase
import com.example.upendrasquizapp.quiz_feature.domain.usecases.quizUseCase.wrapper.QuizUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {


    @Provides
    @Singleton
   fun provideQuizUseCases(getQuizzesByDBUseCase: GetQuizzesByDBUseCase,
                           getQuizzesByNetworkUseCase: GetQuizzesByNetworkUseCase,
                           upsertQuizUseCase: UpsertQuizUseCase,
                           deleteQuizUseCase: DeleteQuizUseCase,
                           deleteQuizByIdUseCase: DeleteQuizBYIdUseCase
   ): QuizUseCases{
      return QuizUseCases(getQuizzesByDBUseCase = getQuizzesByDBUseCase,
          getQuizzesByNetworkUseCase = getQuizzesByNetworkUseCase,
          upsertQuizUseCase = upsertQuizUseCase,
          deleteQuizUseCase = deleteQuizUseCase,
          deleteQuizByIdUseCase = deleteQuizByIdUseCase)
    }

    @Provides
    @Singleton
    fun provideGetQuizzesByDBUseCase(quizRepository: QuizDatabaseRepo): GetQuizzesByDBUseCase{
        return GetQuizzesByDBUseCase(quizRepository)
    }
    @Provides
    @Singleton
    fun provideUpsertQuizUseCase(quizRepository: QuizDatabaseRepo): UpsertQuizUseCase{
        return UpsertQuizUseCase(quizRepository)
    }
    @Provides
    @Singleton
    fun provideDeleteQuizUseCase(quizRepository: QuizDatabaseRepo): DeleteQuizUseCase{
        return DeleteQuizUseCase(quizRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteQuizByIdUseCase(quizRepository: QuizDatabaseRepo): DeleteQuizBYIdUseCase{
        return DeleteQuizBYIdUseCase(quizRepository)
    }

    @Provides
    @Singleton
    fun provideGetQuizzesByNetworkUseCase(quizRepository: QuizRepository): GetQuizzesByNetworkUseCase{
        return GetQuizzesByNetworkUseCase(quizRepository)
    }

}