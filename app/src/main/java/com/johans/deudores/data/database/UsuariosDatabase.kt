package com.johans.deudores.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.johans.deudores.data.database.dao.UsuariosDAO
import com.johans.deudores.data.database.entities.Usuario

@Database(entities = [Usuario::class], version = 1)
abstract class UsuariosDatabase : RoomDatabase() {

    abstract fun UsuariosDAO(): UsuariosDAO
}
