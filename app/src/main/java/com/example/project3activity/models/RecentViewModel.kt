package com.example.project3activity.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project3activity.repositories.RecentGetRepository
import com.example.project3activity.repositories.UserGetRepository
import kotlinx.coroutines.launch

class RecentViewModel : ViewModel() {
    private var _recentList = mutableStateListOf<RecentModel>()

    var errorMessage: String by mutableStateOf("")
    val recentList: List<RecentModel>
        get() = _recentList

    fun getRecentList() {
        viewModelScope.launch {
            val apiClient = RecentGetRepository.getClient()
            try {
                _recentList.clear()
                _recentList.addAll(apiClient.getRecent())
            }
            catch (e: Exception) {
                errorMessage = e.message!!
            }
        }
    }
}