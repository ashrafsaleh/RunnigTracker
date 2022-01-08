package com.example.db

import androidx.room.Database
import androidx.room.TypeConverters

@Database (
    entities = [Run::class],
    version = 1
        )
@TypeConverters (Converters::class)
abstract class RunningDataBase {
    abstract fun getRunDao():RunDAO
}