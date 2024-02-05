package com.testappformornhouse.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FactsEntity::class], version = 1)
abstract class LocalDB: RoomDatabase() {
    abstract fun dao(): LocalDataSource
}