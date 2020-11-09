package com.johans.deudores.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.johans.deudores.Deudores
import com.johans.deudores.R
import com.johans.deudores.data.database.dao.UsuariosDAO
import com.johans.deudores.ui.botton.BottonActivity
import com.johans.deudores.ui.registro.RegistroActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    companion object {
        private const val EMPTY = ""
        private const val SPACE = " "
        private val TAG = LoginActivity::class.simpleName
    }

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        registro_login_btn.setOnClickListener {
            goToResgistroActivity()
        }

        login_btn.setOnClickListener {
            val correo_login = email_login_edit_text.text.toString()
            val contrasena_login = passwor_login_edit_text.text.toString()

            //loginWithFirebase(correo_login,contrasena_login)

            if (contrasena_login.isEmpty() || contrasena_login.isEmpty()) {
                email_login_edit_text.error = getString(R.string.correo)
                email_login_edit_text.requestFocus()
            } else {
                val usuariosDAO: UsuariosDAO = Deudores.databaseU.UsuariosDAO()
                val usuario = usuariosDAO.searchUsuario(correo_login)

                if (usuario != null) {

                    val contrasena_recibido = usuario.contrasena

                    if (contrasena_login == contrasena_recibido) {
                        goToBottonActivity()
                    } else {
                        Toast.makeText(this, "Usuario o contraseña erroneos", Toast.LENGTH_LONG)
                            .show()
                    }

                } else {
                    Toast.makeText(
                        this,
                        "no hay un usuario registrado con este email",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }
    }

    private fun loginWithFirebase(correoLogin: String, contrasenaLogin: String) {
        auth.signInWithEmailAndPassword(correoLogin, contrasenaLogin)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    goToBottonActivity()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun goToBottonActivity() {
        val intent = Intent(this, BottonActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun goToResgistroActivity() {
        val intent = Intent(this, RegistroActivity::class.java)
        startActivity(intent)
    }

}