package com.johans.deudores.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_usuario")
data class Usuario(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "nombre") val nombre: String? = null,
    @ColumnInfo(name = "telefono") val telefono: String? = null,
    @ColumnInfo(name = "correo") val correo: String? = null,
    @ColumnInfo(name = "contraseña") val contrasena: String? = null,
    @ColumnInfo(name = "genero") val genero: String? = null,
    @ColumnInfo(name = "fecha_nacimiento") val fecha: String? = null
)