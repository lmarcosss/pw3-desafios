package com.example.liquigas.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order")
data class Order(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "user_id") var userId: String? = null,
    @ColumnInfo(name = "payment_type") var paymentType: String? = null,
    @ColumnInfo(name = "created_at") var created_at: String? = null,
)