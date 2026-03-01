package com.example.savaari

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var loginbtn: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        email = findViewById(R.id.etEmail)
        password = findViewById(R.id.etPassword)
        loginbtn = findViewById(R.id.btnLogin)

        //Firebase Auth
        mAuth = FirebaseAuth.getInstance()

        // Login Button Click
        loginbtn.setOnClickListener {

            val userEmail = email.text.toString().trim()
            val userPass = password.text.toString().trim()

            //  Validate Email
            if (userEmail.isEmpty()) {
                email.error = "Enter email"
                email.requestFocus()
                return@setOnClickListener
            }

            //  Validate Password
            if (userPass.isEmpty()) {
                password.error = "Enter password"
                password.requestFocus()
                return@setOnClickListener
            }

            // Firebase Login
            mAuth.signInWithEmailAndPassword(userEmail, userPass)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        Toast.makeText(
                            this,
                            "Login Successful",
                            Toast.LENGTH_SHORT
                        ).show()

                        startActivity(Intent(this, MainActivity::class.java))
                        finish()

                    } else {

                        Toast.makeText(this,"Login failed", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    //  Auto-login if already logged in
    override fun onStart() {
        super.onStart()

        if (mAuth.currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}