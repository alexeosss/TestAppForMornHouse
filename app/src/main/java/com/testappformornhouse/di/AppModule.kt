package com.testappformornhouse.di

import com.testappformornhouse.data.RemoteDataSource
import com.testappformornhouse.data.Repository
import com.testappformornhouse.data.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {



    @Provides
    @Singleton
    fun provideRepository(remoteDataSource: RemoteDataSource) : Repository {
        return RepositoryImpl(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(): RemoteDataSource {
        return Retrofit.Builder()
            .baseUrl("http://numbersapi.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(RemoteDataSource::class.java)
    }

}