package com.example.android.assignment.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LoginEntity::class], version = 1)
abstract class AssignmentDatabase : RoomDatabase(){

    abstract fun loginDao(): LoginDao

}