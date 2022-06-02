package com.example.liquigas.dao

import androidx.room.*
import com.example.liquigas.entity.Order
import com.example.liquigas.entity.User

@Dao
interface OrderDAO {
    @Query("SELECT * FROM [Order] WHERE id = :id")
    fun getOrderById(id: String): Order

    @Query( "SELECT * FROM [Order] WHERE user_id = :userId ORDER BY created_at DESC")
    fun getUserOrders(userId: String): List<Order>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(order: Order): Long

    @Update
    fun update(vararg order: Order)

    @Delete
    fun delete(order: Order)
}