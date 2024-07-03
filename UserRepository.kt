//package com.example.jitsimeet
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.asLiveData
//
//class UserRepository(private val userDao: UserDao) {
//    val allUsers: LiveData<List<User>> = userDao.getAllUsers()
//
//    suspend fun insert(user: User) {
//        userDao.insert(user)
//    }
//
////    suspend fun get(): LiveData<List<User>>{
////        return userDao.getAllUsers()
////    }
//}
