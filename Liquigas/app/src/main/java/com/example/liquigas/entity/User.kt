package com.example.liquigas.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "name") var name: String? = null,
    @ColumnInfo(name = "address") var address: String? = null,
    @ColumnInfo(name = "payment_preference") var payment_preference: String? = "DINHEIRO",
)