package com.example.upendrasquizapp.profile_feature.data.di

import android.content.Context
import com.example.upendrasquizapp.profile_feature.data.repository.UserPrefsProtoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    fun provideUserRepo(@ApplicationContext context: Context) : UserPrefsProtoRepository{
       return UserPrefsProtoRepository(context)
    }

}