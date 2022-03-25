package com.parkjunsik.testdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LoginDao{
    @Query("Select * From LoginEntity")
    fun getLogin():LoginEntity

    @Query("Delete From LoginEntity")
    fun deleteLogin()

    @Insert
    fun insetLogin(loginEntity: LoginEntity)
}