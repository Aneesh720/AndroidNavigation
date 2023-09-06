package com.example.testing.View

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.testing.MainActivity
import com.example.testing.R
import com.example.testing.repository.UserRepository
import com.example.testing.services.LoginVerifier

class Login : AppCompatActivity() {
    lateinit var emailInput: EditText
    lateinit var passwordInput: EditText
    lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initialization()
        loginButton.setOnClickListener(View.OnClickListener {
            loginPressed()
        })
    }

    fun loginPressed() {

        var loginVerifier= LoginVerifier()
        val result = loginVerifier.conditionSatified(emailInput.text.toString(), passwordInput.text.toString())
        if (result == "Success") {
            ///Checking the Login Activity
            var userRepository = UserRepository()
            userRepository.loginUser(
                email = emailInput.text.toString(),
                password = passwordInput.text.toString()
            )
            Log.d("email", emailInput.text.toString())
            /// Move to the MainActivity
            val intent = Intent(
                this, MainActivity::class.java
            )
            startActivity(intent)
            finish()
        } else {
            ///Display The Toast Message
        }
        Log.d("result", result)
    }


     fun initialization() {
        emailInput = findViewById(R.id.email)
        passwordInput = findViewById(R.id.password)
        loginButton = findViewById(R.id.loginButton)

    }


}