package com.testappformornhouse.data

import com.testappformornhouse.data.local.FactsEntity

interface Repository {
    suspend fun getFactForNumber(number: String): String

    suspend fun getFactForRandomNumber(): String

    suspend fun putFact(factsEntity: FactsEntity)

    suspend fun getAllFacts(): List<FactsEntity>
}

