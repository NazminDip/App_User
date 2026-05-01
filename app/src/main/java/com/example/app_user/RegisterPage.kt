package com.example.app_user

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app_user.databinding.ActivityRegisterPageBinding

class RegisterPage : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.registerBtn2.setOnClickListener {

            val email = binding.emailET.text.toString()
            val password = binding.passwordET.text.toString()
            val confirmpassword = binding.confirmpasswordET.text.toString()

            //Fieldcheck

            if (email.isEmpty()) {
                binding.emailET.error = "Email is required"

                {

                    if (password.isEmpty()) {
                        binding.passwordET.error = "Password  is required"
                    }
                    if (confirmpassword.isEmpty()) {
                        binding.confirmpasswordET.error = "Confirm Password  is required"
                    }
                }

                // PASSWord  VAlidation
                if (password != confirmpassword) {
                    Toast.makeText(this, "password not match", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener

                }


            }
            //Success

            Toast.makeText(this, "Register Successfully", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, WelcomePage::class.java))

        }
    }

}


