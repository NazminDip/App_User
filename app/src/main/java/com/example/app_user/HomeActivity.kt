package com.example.app_user

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_user.adapter.UserAdapter
import com.example.app_user.databinding.ActivityHomeBinding
import com.example.app_user.viewModel.UserViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: UserViewModel
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]//what is viewModel Provider
        viewModel.usersLiveData.observe(this) { list ->
            binding.countTv.text = "Total Notes : ${list.size}"

            val adapter = UserAdapter(
                list,
                onEdit = { user ->
                    val intent = Intent(this@HomeActivity, AddActivity::class.java)
                    intent.putExtra("id", user.id)
                    intent.putExtra("name", user.name)
                    intent.putExtra("address", user.address)
                    intent.putExtra("phone", user.phone)
                    startActivity(intent)

                },
                onDelete = { user ->
                    val view = layoutInflater.inflate(R.layout.dialog_delete, null)
                    val dialog = MaterialAlertDialogBuilder(this)
                        .setView(view)
                        .create()
                    view.findViewById<Button>(R.id.btnConfirmDelete).setOnClickListener {
                        viewModel.deleteFromViewModel(user)
                        dialog.dismiss()
                    }
                    dialog.show()
                }
            )
            binding.recyclerView.adapter = adapter
        }

        binding.logout.setOnClickListener {
            startActivity(Intent(this@HomeActivity, WelcomePage::class.java))
        }

// SEARCH

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(!newText.isNullOrEmpty()){
                    viewModel.searchFromViewModel(newText)
                }else{
                    viewModel.loadUsersFromViewModel()
                }
                return true
            }

        }


        )
    }
    /////////

    override fun onResume() {
        super.onResume()
        viewModel.loadUsersFromViewModel()
    }
}