package com.example.project3activity.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project3activity.repositories.UserGetRepository
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private var _userList = mutableStateListOf<UserModel>()

    var errorMessage: String by mutableStateOf("")
    val userList: List<UserModel>
        get() = _userList

    fun getUserList() {
        viewModelScope.launch {
            val apiClient = UserGetRepository.getClient()
            try {
                _userList.clear()
                _userList.addAll(apiClient.getUser())
            }
            catch (e: Exception) {
                errorMessage = e.message!!
            }
        }
    }
}