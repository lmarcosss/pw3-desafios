package com.example.liquigas.dao

import androidx.room.*
import com.example.liquigas.entity.Item

@Dao
interface ItemDAO {
    @Query("SELECT * FROM order_item WHERE id = :id")
    fun getItemById(id: String): Item

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Item): Long

    @Update
    fun update(vararg item: Item)

    @Delete
    fun delete(item: Item)

    @Query("SELECT * FROM order_item")
    fun getAll(): List<Item>

    @Query("SELECT * FROM order_item WHERE order_id = :orderId")
    fun getOrderWithItemByOrder(orderId: String): List<Item>
}