package com.example.liquigas.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.liquigas.entity.Item
import com.example.liquigas.entity.Order
import com.example.liquigas.entity.User

@Database(entities = [User::class, Order::class, Item::class], version = 12, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDAO(): UserDAO
    abstract fun orderDAO(): OrderDAO
    abstract fun itemDAO(): ItemDAO

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): AppDatabase {
            if (instance == null) {
                instance =
                    Room.databaseBuilder(ctx.applicationContext, AppDatabase::class.java, "liquigas_database")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return instance!!
        }
    }
}