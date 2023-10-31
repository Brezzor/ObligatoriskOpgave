package com.example.obligatoriskopgave

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.obligatoriskopgave.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginRegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var activityIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        title = "Login"

        auth = Firebase.auth

        binding.loginButton.setOnClickListener {
            val email = binding.EmailAddress.text.trim().toString()
            val password = binding.Password.text.trim().toString()
            if (email.isEmpty() and password.isEmpty()) {
                Toast.makeText(baseContext,"No credentials.",Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            if (email.isEmpty()) {
                Toast.makeText(baseContext,"No email.",Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                Toast.makeText(baseContext,"No password.",Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        baseContext,
                        "Authentication successful.",
                        Toast.LENGTH_SHORT
                    ).show()
                    activityIntent = Intent(this, MainActivity::class.java)
                    startActivity(activityIntent)
                    finish()
                } else {
                    Toast.makeText(
                        baseContext,
                        task.exception?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                    return@addOnCompleteListener
                }
            }
        }

        binding.registerButton.setOnClickListener {
            val email = binding.EmailAddress.text.trim().toString()
            if (email.isEmpty()) {
                Toast.makeText(
                    baseContext,
                    "No email.",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            val password = binding.Password.text.trim().toString()
            if (password.isEmpty()) {
                Toast.makeText(
                    baseContext,
                    "No password.",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            baseContext,
                            "Registered successful.",
                            Toast.LENGTH_SHORT
                        ).show()
                        activityIntent = Intent(this, MainActivity::class.java)
                        startActivity(activityIntent)
                        finish()
                    } else {
                        Toast.makeText(
                            baseContext,
                            task.exception?.message,
                            Toast.LENGTH_SHORT
                        ).show()
                        return@addOnCompleteListener
                    }
                }
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null)
        {
            activityIntent = Intent(this, MainActivity::class.java)
            startActivity(activityIntent)
            finish()
        }
    }
}