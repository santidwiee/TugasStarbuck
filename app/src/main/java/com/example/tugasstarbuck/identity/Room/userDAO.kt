package com.example.tugasstarbuck.identity.Room

import androidx.room.*

@Dao
interface userDAO {
    @Query("SELECT * FROM usermodel where email= :mail and password= :password")
    fun getUser(mail: String, password: String): usermodel

    @Insert
    fun insert(user: usermodel)

    @Update
    fun update(user: usermodel)

    @Delete
    fun delete(user: usermodel)
}
