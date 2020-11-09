package com.johans.deudores

import android.app.Application
import androidx.room.Room
import com.johans.deudores.data.database.DeudoresDatabase
import com.johans.deudores.data.database.UsuariosDatabase

class Deudores : Application() {
    companion object {
        lateinit var database: DeudoresDatabase
        lateinit var databaseU: UsuariosDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            DeudoresDatabase::class.java,
            "deudor_DB"
        ).allowMainThreadQueries()
            .build()

        databaseU = Room.databaseBuilder(this, UsuariosDatabase::class.java, "usuario_DB")
            .allowMainThreadQueries().build()
    }
}