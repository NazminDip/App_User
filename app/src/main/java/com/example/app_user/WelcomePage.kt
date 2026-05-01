package com.example.app_user

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity




import android.annotation.SuppressLint
import android.content.Intent

import android.widget.Toast
import com.example.app_user.databinding.ActivityWelcomePageBinding


class WelcomePage : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomePageBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        binding = ActivityWelcomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ///  LOGIN
        binding.registerBtn1.setOnClickListener {
            startActivity(Intent(this, RegisterPage::class.java))

        }

        binding.loginBtn1.setOnClickListener {
            val email = binding.emailET.text.toString()
            val password = binding.passwordET.text.toString()

            // VALIDATION
            if (email.isEmpty()) {
                binding.emailET.error = "Email is required"

            } else {

                if (password.isEmpty()) {
                    binding.passwordET.error = "Password  is required"
                }
            }



/////////////////////////
            //Validation EMAIL
            if (!email.endsWith("@gmail.com")) {
                Toast.makeText(this, "Email must be @gmail.com", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }
            // Password validation: at least 8 chars, 1 uppercase, 1 lowercase, 1 number, 1 special char
            val passwordPattern =
                Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}\$")
            if (!password.matches(passwordPattern)) {
                Toast.makeText(
                    this,
                    "Password must be at least 8 characters and include uppercase, lowercase, number, and special character",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }


            Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@WelcomePage, HomeActivity::class.java))


        }

    }


    }
