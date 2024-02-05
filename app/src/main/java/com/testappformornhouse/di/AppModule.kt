package com.testappformornhouse.di

import android.content.Context
import androidx.room.Room
import com.testappformornhouse.data.remote.RemoteDataSource
import com.testappformornhouse.data.Repository
import com.testappformornhouse.data.RepositoryImpl
import com.testappformornhouse.data.local.LocalDB
import com.testappformornhouse.data.local.LocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {



    @Provides
    @Singleton
    fun provideRepository(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource) : Repository {
        return RepositoryImpl(remoteDataSource, localDataSource)
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

    @Provides
    @Singleton
    fun provideLocalSource(@ApplicationContext context: Context): LocalDataSource{
        return Room.databaseBuilder(context, LocalDB::class.java, "db").build().dao()
    }

}