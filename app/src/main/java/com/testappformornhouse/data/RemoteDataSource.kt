package com.testappformornhouse.data

import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteDataSource {

    @GET("{numberFromScreen}")
    suspend fun getFactForNumber(@Path("numberFromScreen") numberFromScreen: String): String

    @GET("random/math")
    suspend fun getFactForRandomNumber(): String

}