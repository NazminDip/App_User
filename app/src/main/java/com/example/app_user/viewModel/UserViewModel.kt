package com.example.app_user.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.app_user.Repository.UserRepository

import com.example.app_user.database.AppDatabase
import com.example.app_user.model.User

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext



class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : UserRepository

    val usersLiveData = MutableLiveData<List<User>>()

    init {

        val dao = AppDatabase.getDatabase(application).userDao()
        repository= UserRepository(dao)
        loadUsersFromViewModel()
    }

    fun loadUsersFromViewModel() {
        viewModelScope.launch(Dispatchers.IO) {
            val users =repository.getAllUsersFromRepo()
            withContext(Dispatchers.Main){
                usersLiveData.value= users
            }
        }
    }

    fun insertFromViewModel(user: User){
        viewModelScope.launch(Dispatchers.IO){
            repository.insert(user)
            loadUsersFromViewModel()
        }

    }
    fun updateFromViewModel(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(user)
            loadUsersFromViewModel()
        }

    }
    fun deleteFromViewModel(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(user)
            loadUsersFromViewModel()
        }
    }
    fun searchFromViewModel(query: String){
        viewModelScope.launch(Dispatchers.IO){
            val result = repository.searchUsersFromRepo(query)
            withContext(Dispatchers.Main){
                usersLiveData.value=result
            }
        }

    }




}