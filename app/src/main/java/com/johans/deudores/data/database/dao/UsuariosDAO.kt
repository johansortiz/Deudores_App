package com.johans.deudores.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.johans.deudores.data.database.entities.Usuario

@Dao
interface UsuariosDAO {

    @Insert
    fun insertUsuario(usuario: Usuario)

    @Query("SELECT * FROM tabla_usuario WHERE correo LIKE :correo")
    fun searchUsuario(correo: String): Usuario
/*
    @Delete
    fun deleteDeudor(deudor: Deudor)

    @Update
    fun updateDeudor(deudor: Deudor)*/

}