package com.example.tugasstarbuck.identity.Room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [usermodel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val userDao: userDAO
}