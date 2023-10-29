package com.example.obligatoriskopgave

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.obligatoriskopgave.databinding.ActivityLoginBinding
import com.example.obligatoriskopgave.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var activityIntent: Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        auth = Firebase.auth

        binding.registerButton.setOnClickListener {
            activityIntent = Intent(this, MainActivity::class.java)
            startActivity(activityIntent)
            finish()
        }

        binding.loginButton.setOnClickListener {
            activityIntent = Intent(this, LoginActivity::class.java)
            startActivity(activityIntent)
            finish()
        }
    }
}