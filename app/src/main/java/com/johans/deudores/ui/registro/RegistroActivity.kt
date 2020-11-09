package com.johans.deudores.ui.registro

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.johans.deudores.Deudores
import com.johans.deudores.R
import com.johans.deudores.data.database.dao.UsuariosDAO
import com.johans.deudores.data.database.entities.Usuario
import com.johans.deudores.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_registro.*
import java.sql.Types
import java.text.SimpleDateFormat
import java.util.*

class RegistroActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    companion object {
        private const val EMPTY = ""
        private const val SPACE = " "
        private const val CITY_S = "Seleccione una ciudad"
        private const val CITY_E = "Choose city"
        private val TAG = RegistroActivity::class.simpleName
    }

    private var fechaNacimiento: String = ""
    private var cal = Calendar.getInstance()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        auth = FirebaseAuth.getInstance()

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfmonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH, dayOfmonth)

                val format = "MM/dd/yyyy"
                val sdf = SimpleDateFormat(format, Locale.US)
                fechaNacimiento = sdf.format(cal.time).toString()
                btn_show.text = fechaNacimiento
            }
       btn_show.setOnClickListener{
            DatePickerDialog(
                 this,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }


        registrar_button.setOnClickListener {
            val nombre = nombre_input_text.text.toString()
            val correo = correo_edit_text.text.toString()
            val telefono = numero_edit_text.text.toString()
            val contrasena = contrasena_edit_text.text.toString()
            val confirmar = confirmar_edit_text.text.toString()
            val genero =
                if (masculino_radioB.isChecked) getString(R.string.masculino) else getString(
                    R.string.femenino
                )

            val fecha = btn_show.text.toString()

            when {
                nombre.isEmpty() -> {
                    nombre_input_text.error = getString(R.string.nombres)
                    nombre_input_text.requestFocus()
                }
                correo.isEmpty() -> {
                    correo_edit_text.error = getString(R.string.correo)
                    correo_edit_text.requestFocus()
                }
                telefono.isEmpty() -> {
                    numero_edit_text.error = getString(R.string.telefono)
                    numero_edit_text.requestFocus()
                }
                contrasena.isEmpty() -> {
                    contrasena_edit_text.error = getString(R.string.contraseña)
                    contrasena_edit_text.requestFocus()
                }
                fecha == getText(R.string.fecha_nacimiento)-> {
                    Toast.makeText(this, getString(R.string.fecha_nacimiento), Toast.LENGTH_LONG)
                        .show()
                    confirmar_edit_text.requestFocus()
                }
                contrasena != confirmar -> {
                    confirmar_edit_text.error = getString(R.string.contraseña_no_coincide)
                    confirmar_edit_text.requestFocus()
                }
                else -> {

                    //registroEnFirebase(correo,contrasena)

                    var usuariosDAO: UsuariosDAO = Deudores.databaseU.UsuariosDAO()
                    var usuario = usuariosDAO.searchUsuario(correo)

                    if (usuario != null) {
                        Toast.makeText(
                            this,
                            "Ya hay un usuario registrado con este email",
                            Toast.LENGTH_SHORT
                        ).show()
                        correo_edit_text.requestFocus()
                    } else {
                        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()

                        usuario =
                            Usuario(Types.NULL, nombre, telefono, correo, contrasena, genero, fecha)
                        usuariosDAO = Deudores.databaseU.UsuariosDAO()
                        usuariosDAO.insertUsuario(usuario)

                        onBackPressed()
                    }

                }
            }
        }
    }

    private fun registroEnFirebase(correo: String, contrasena: String) {
        auth.createUserWithEmailAndPassword(correo, contrasena)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    goToLoginActivity()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun goToLoginActivity() {
        onBackPressed()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onStart() {
        super.onStart()
        Log.d("Método","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Método","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Método","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Método","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Método","onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Método","onRestart")
    }

    fun showDatePickerDialog(view: View) {}
}