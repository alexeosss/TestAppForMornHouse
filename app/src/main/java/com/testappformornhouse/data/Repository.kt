package com.testappformornhouse.data

interface Repository {
    suspend fun getFactForNumber(number: String): String

    suspend fun getFactForRandomNumber(): String
}

