package com.johans.deudores.data.database.dao

import androidx.room.*
import com.johans.deudores.data.database.entities.Deudor

@Dao
interface DeudorDAO {

    @Insert
    fun insertDeudor(deudor: Deudor)

    @Query("SELECT * FROM tabla_deudor WHERE nombre LIKE :nombre")
    fun searchDeudor(nombre: String): Deudor

    @Delete
    fun deleteDeudor(deudor: Deudor)

    @Update
    fun updateDeudor(deudor: Deudor)

    @Query("SELECT * FROM tabla_deudor")
    fun getDeudores(): List<Deudor>
}