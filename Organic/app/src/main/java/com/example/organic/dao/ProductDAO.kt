package com.example.organic.dao

import androidx.room.*
import com.example.organic.entity.Product

@Dao
interface ProductDAO {
    @Query("SELECT * FROM Product")
    fun getAllProducts(): MutableList<Product>

    @Query("SELECT * FROM Product WHERE name = :name")
    fun getProductByName(name: String): MutableList<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg product: Product)

    @Update
    fun update(vararg product: Product)

    @Delete
    fun delete(product: Product)
}