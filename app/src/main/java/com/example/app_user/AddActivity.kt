package com.example.app_user

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.app_user.databinding.ActivityAddBinding
import com.example.app_user.model.User
import com.example.app_user.viewModel.UserViewModel


class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding

    private lateinit var viewModel: UserViewModel

    private var userid = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)



        viewModel = ViewModelProvider(this)[UserViewModel::class.java]


        userid = intent.getIntExtra("id", -1)

        if (userid != -1) {

            binding.nameET.setText(intent.getStringExtra("name"))
            binding.dobET.setText(intent.getStringExtra("dob"))
            binding.addressET.setText(intent.getStringExtra("address"))
            binding.phoneET.setText(intent.getStringExtra("phone"))
            binding.emailET.setText(intent.getStringExtra("email"))


        }

        binding.button.setOnClickListener {
            val name = binding.nameET.text.toString()
            val dob = binding.dobET.text.toString()
            val address = binding.addressET.text.toString()
            val phone = binding.phoneET.text.toString()
            val email = binding.emailET.text.toString()

            //  VALIDATION
            //VALIDATION
            if (name.isEmpty()) {
                binding.nameET.error = "enter your name"
                binding.nameET.requestFocus()
                return@setOnClickListener
            }
            if (dob.isEmpty()) {
                binding.dobET.error = "enter your Birth date"
                binding.dobET.requestFocus()
                return@setOnClickListener
            }

            if (address.isEmpty()) {
                binding.addressET.error = "enter your address"
                binding.addressET.requestFocus()
                return@setOnClickListener
            }

            if (phone.isEmpty()) {
                binding.phoneET.error = "enter your phone Number"
                binding.phoneET.requestFocus()
                return@setOnClickListener
            }
            if (email.isEmpty()) {
                binding.emailET.error = "enter your email address"
                binding.emailET.requestFocus()
                return@setOnClickListener
            }

                if (userid == -1) {
                    //insert
                    viewModel.insertFromViewModel(
                        User(
                            name = name,
                            dob = dob,
                            address = address,
                            phone = phone,
                            email = email
                        )
                    )

                } else {
                    //Update
                    viewModel.updateFromViewModel(
                        User(
                            id = userid,
                            name = name,
                            dob = dob,
                            address = address,
                            phone = phone,
                            email = email
                        )
                    )
                }
                Toast.makeText(this@AddActivity, "data saved successfully", Toast.LENGTH_SHORT)
                    .show()
            startActivity(Intent(this@AddActivity, HomeActivity::class.java))
                finish()


            }
        binding.back.setOnClickListener {
            startActivity(Intent(this@AddActivity, HomeActivity::class.java))
        }


        }
    }
