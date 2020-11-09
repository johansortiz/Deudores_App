package com.johans.deudores.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.johans.deudores.data.database.dao.DeudorDAO
import com.johans.deudores.data.database.entities.Deudor

@Database(entities = [Deudor::class], version = 1)
abstract class DeudoresDatabase : RoomDatabase() {

    abstract fun DeudorDAO(): DeudorDAO
}
