package com.example.bookify

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        val etName = findViewById<TextInputEditText>(R.id.etName)
        val etEmail = findViewById<TextInputEditText>(R.id.etEmailReg)
        val etPass = findViewById<TextInputEditText>(R.id.etPasswordReg)
        val etConfirm = findViewById<TextInputEditText>(R.id.etConfirmReg)
        val btnRegister = findViewById<MaterialButton>(R.id.btnRegister)

        findViewById<ImageButton>(R.id.btnBack).setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        btnRegister.setOnClickListener {
            val name = etName.text?.toString()?.trim().orEmpty()
            val email = etEmail.text?.toString()?.trim().orEmpty()
            val pass = etPass.text?.toString().orEmpty()
            val confirm = etConfirm.text?.toString().orEmpty()

            when {
                name.length < 2 -> toast("Enter your full name")
                !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> toast("Enter a valid email")
                pass.length < 6 -> toast("Password must be at least 6 characters")
                pass != confirm -> toast("Passwords do not match")
                AppData.users.any { it.email.equals(email, true) } -> toast("Email already registered")
                else -> {
                    AppData.users.add(AppData.User(name, email, pass))
                    toast("Account created. Please login")
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
            }
        }
    }

    private fun toast(msg: String) =
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
