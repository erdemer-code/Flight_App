package com.example.flightappp.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.flightappp.R
import com.example.flightappp.utils.Constant
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private val email = Constant.EMAIL
    private val password = Constant.PASSWORD
    private var isRemember = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonLogin.setOnClickListener {
            if (email == editTextEmail.text.toString() && password == editTextTextPassword.text.toString()){
                if (isRemember)
                    rememberMe()
                Toast.makeText(this,"Giriş Başarılı",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, FlightActivity::class.java))
                finish()
            } else {
                Toast.makeText(this,"Bilgileriniz hatalıdır. Lütfen bilgilerini kontrol ediniz.",
                Toast.LENGTH_SHORT).show()
                editTextTextPassword.setText("")
            }

        }
        checkBoxRemember.setOnCheckedChangeListener{ _, isChecked ->
            isRemember = isChecked
        }
    }

    private fun rememberMe() {
        val sharedPreferences = getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
        val myEdit = sharedPreferences.edit()
        myEdit.putString("email",editTextEmail.text.toString())
        myEdit.putString("password",editTextTextPassword.text.toString())
        myEdit.putBoolean("isRemember",isRemember)
        myEdit.commit()
    }
}