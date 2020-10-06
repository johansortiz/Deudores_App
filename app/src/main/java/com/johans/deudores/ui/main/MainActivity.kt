package com.johans.deudores.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import com.johans.deudores.R
import com.johans.deudores.ui.buscar.BuscarFragment
import com.johans.deudores.ui.crear.CrearFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       /* val manager = supportFragmentManager
        val transaction = manager.beginTransaction()

        val crearFragment = CrearFragment()
        transaction.add(R.id.contenedor, crearFragment).commit()*/
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        when(item.itemId){
            R.id.menu_crear->{
                val crearFragment = CrearFragment()
                transaction.replace(R.id.contenedor, crearFragment).commit()
            }
            R.id.menu_buscar->{
                val buscarFragment = BuscarFragment()
                transaction.replace(R.id.contenedor, buscarFragment).commit()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_overflow,menu)
        return true
    }

}