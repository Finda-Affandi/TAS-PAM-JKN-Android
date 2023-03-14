package com.example.project3activity.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project3activity.repositories.ArticleGetRepository
import kotlinx.coroutines.launch

class ArticleViewModel : ViewModel() {
    private var _articleList = mutableStateListOf<ArticleModel>()

    var errorMessage: String by mutableStateOf("")
    val articleList: List<ArticleModel>
        get() = _articleList

    fun getArticleList() {
        viewModelScope.launch {
            val apiClient = ArticleGetRepository.getClient()
            try {
                _articleList.clear()
                _articleList.addAll(apiClient.getArticle())
            }
            catch (e: Exception) {
                errorMessage = e.message!!
            }
        }
    }
}