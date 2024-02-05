package com.testappformornhouse.data

class RepositoryImpl(val remoteDataSource: RemoteDataSource) : Repository {

    override suspend fun getFactForRandomNumber(): String {
        return remoteDataSource.getFactForRandomNumber()
    }

    override suspend fun getFactForNumber(number: String): String {
        return remoteDataSource.getFactForNumber(number)
    }

}