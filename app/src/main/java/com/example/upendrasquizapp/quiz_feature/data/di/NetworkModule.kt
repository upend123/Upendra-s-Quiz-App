package com.example.upendrasquizapp.quiz_feature.data.di


import com.example.upendrasquizapp.quiz_feature.data.remote.api.QuizApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val baseUrl = "https://opentdb.com/"

    @Singleton
    @Provides fun provideRetrofitInstance() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides fun provideQuizApi(retrofit: Retrofit) : QuizApi {
        return retrofit.create(QuizApi::class.java)
    }


}