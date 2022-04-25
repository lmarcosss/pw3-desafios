package com.example.organic.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.organic.entity.Product


@Database(entities = [Product::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDAO(): ProductDAO

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): AppDatabase {
            if (instance == null) {
                instance =
                    Room.databaseBuilder(ctx.applicationContext, AppDatabase::class.java, "organic_database")
                        .allowMainThreadQueries()
                        .build()
            }
            return instance!!
        }
    }
}

