package com.example.liquigas.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order_item")
data class Item(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "product") var product: String? = null,
    @ColumnInfo(name = "quantity") var quantity: Int? = 0,
    @ColumnInfo(name = "order_id") var orderId: Int? = null,
)