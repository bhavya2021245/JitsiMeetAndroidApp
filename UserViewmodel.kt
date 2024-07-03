//package com.example.jitsimeet
//
//import android.app.Application
//import android.util.Log
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch
//
//class UserViewModel(application: Application) : AndroidViewModel(application) {
//
//    val repository: UserRepository
//
//
//    init {
//        val userDao = AppDatabase.getDatabase(application).userDao()
//        repository = UserRepository(userDao)
////        allUsers = repository.allUsers
//    }
//
//    val allUsers: LiveData<List<User>> = repository.allUsers
//
//    fun insert(user : User) = viewModelScope.launch(Dispatchers.IO) {
//        repository.insert(user)
//    }
//
////    fun getall(): LiveData<List<User>> {
////        var data: LiveData<List<User>> = MutableLiveData(emptyList())
////        viewModelScope.launch(Dispatchers.IO) {
////            data = repository.get()
////        }
////        return data
////    }
//}