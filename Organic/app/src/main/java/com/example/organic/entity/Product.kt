package com.example.organic.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "product")
data class Product(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "name") var name: String? = null,
    @ColumnInfo(name = "description") var description: String? = null,
    @ColumnInfo(name = "amount_value") var amountValue: Double? = 0.0,
    @ColumnInfo(name = "provider_name") var providerName: String? = null
): Serializable