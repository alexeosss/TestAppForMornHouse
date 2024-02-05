package com.testappformornhouse.di

import android.content.Context
import com.testappformornhouse.data.Repository
import com.testappformornhouse.data.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {



    @Provides
    @Singleton
    fun provideRepository() : Repository {
        return RepositoryImpl()
    }

}