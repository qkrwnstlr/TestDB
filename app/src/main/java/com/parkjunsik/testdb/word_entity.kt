package com.parkjunsik.testdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LoginEntity(
    val username:String,
    val password:String
){
    @PrimaryKey(autoGenerate = true)var id:Int=0
}