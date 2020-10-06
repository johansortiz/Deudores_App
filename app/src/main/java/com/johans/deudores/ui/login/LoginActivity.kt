package com.johans.deudores.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.johans.deudores.R
import com.johans.deudores.ui.registro.RegistroActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val datos_recibidos = intent.extras
        val correo_recibido = datos_recibidos?.getString("correo")
        val contrasena_recibido = datos_recibidos?.getString("contrasena")


        registro_login_btn.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
            finish()
        }

        login_btn.setOnClickListener {
            val correo_login = email_login_edit_text.text.toString()
            val contrasena_login = passwor_login_edit_text.text.toString()

            when{
                correo_login.isEmpty() -> {
                    Toast.makeText(this, "Falta Correo", Toast.LENGTH_LONG).show()
                    email_login_edit_text.requestFocus()
                }
                contrasena_login.isEmpty() -> {
                    Toast.makeText(this, "Falta Contraseña", Toast.LENGTH_LONG).show()
                    passwor_login_edit_text.requestFocus()
                }
                correo_login == correo_recibido && contrasena_login == contrasena_recibido ->{
                   //val intent = Intent(this, MainActivity::class.java)
                    //intent.putExtra("correo_main",correo_login)
                    //intent.putExtra("contra_main",contrasena_login)
                    //startActivity(intent)
                    //finish()
                    Toast.makeText(this, "Bienvenido", Toast.LENGTH_LONG).show()
                }
                else -> {
                    Toast.makeText(this, "Usuario o contraseña erroneos", Toast.LENGTH_LONG).show()
                }

            }
        }
        }

    }