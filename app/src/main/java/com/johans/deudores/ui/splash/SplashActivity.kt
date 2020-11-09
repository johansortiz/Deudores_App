package com.johans.deudores.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.johans.deudores.R
import com.johans.deudores.ui.botton.BottonActivity
import com.johans.deudores.ui.login.LoginActivity
import java.util.*
import kotlin.concurrent.timerTask

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val timer = Timer()
        timer.schedule(
            timerTask {
                /* val auth = FirebaseAuth.getInstance().currentUser
                 if(auth == null){
                     goToLoginActivity()
                 }else{
                     goToBottonAvtivity()
                 }*/
                goToLoginActivity()
            }, 2000
        )

    }

    private fun goToBottonAvtivity() {
        val intent = Intent(this, BottonActivity::class.java)
        startActivity(intent)
        finish()
    }


    fun goToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}