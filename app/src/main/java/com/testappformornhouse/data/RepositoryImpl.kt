package com.testappformornhouse.data

import com.testappformornhouse.data.local.FactsEntity
import com.testappformornhouse.data.local.LocalDB
import com.testappformornhouse.data.local.LocalDataSource
import com.testappformornhouse.data.remote.RemoteDataSource

class RepositoryImpl(val remoteDataSource: RemoteDataSource, val localDataSource: LocalDataSource) : Repository {


    override suspend fun getFactForRandomNumber(): String {
        return remoteDataSource.getFactForRandomNumber()
    }

    override suspend fun putFact(factsEntity: FactsEntity) {
        localDataSource.addFact(factsEntity)
    }

    override suspend fun getAllFacts(): List<FactsEntity> {
        return localDataSource.getAllFacts().reversed()
    }

    override suspend fun getFactForNumber(number: String): String {
        return remoteDataSource.getFactForNumber(number)
    }

}