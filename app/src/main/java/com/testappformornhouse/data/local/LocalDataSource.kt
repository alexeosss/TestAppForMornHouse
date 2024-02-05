package com.testappformornhouse.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LocalDataSource {

    @Query("SELECT * FROM db")
    fun getAllFacts(): List<FactsEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFact(factsEntity: FactsEntity)


}