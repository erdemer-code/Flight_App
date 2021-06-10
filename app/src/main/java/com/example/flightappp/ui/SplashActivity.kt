package com.example.flightappp.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flightappp.R
import java.util.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)
        if (rememberData()){
            Timer().schedule(object : TimerTask() {
                override fun run() {
                    startActivity(Intent(this@SplashActivity, FlightActivity::class.java))
                    finish()
                }
            }, 3000)
        } else {
            Timer().schedule(object : TimerTask() {
                override fun run() {
                    startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                    finish()
                }
            }, 3000)
        }

    }

    private fun rememberData(): Boolean {
        val sharedPreferences = getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
        val email = sharedPreferences.getString("email","")
        val password = sharedPreferences.getString("password","")
        val isRemember = sharedPreferences.getBoolean("isRemember",false)

        if (email?.isNotEmpty() == true && password?.isNotEmpty() == true){
            return true
        }
        return false
    }
}


