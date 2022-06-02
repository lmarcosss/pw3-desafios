package com.example.liquigas.dao

import androidx.room.*
import com.example.liquigas.entity.User

@Dao
interface UserDAO {
    @Query("SELECT * FROM User WHERE id = :id")
    fun getUserById(id: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User): Long

    @Query("SELECT * FROM User WHERE name = :name")
    fun getUserByName(name: String): User

    @Update
    fun update(vararg user: User)

    @Delete
    fun delete(user: User)
}