package com.example.project3activity.tryRestApi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.project3activity.models.ArticleViewModel
import com.example.project3activity.models.JknUserViewModel

@Composable
fun MainScreenView(
    vm: ArticleViewModel
){
    LaunchedEffect(
        Unit,
        block = {
            vm.getArticleList()
        }
    )

    Column() {
        if (vm.errorMessage.isEmpty()) {
            LazyColumn(modifier = Modifier.padding(18.dp)){
                items(vm.articleList.size) {index ->
                    Text(text = vm.articleList[index].content)
//                    if(vm.toDoList[index].username == "Cool") {
//                        Text(text = vm.toDoList[index].username)
//                    }
                }
            }
        }
        else {
            Text(text = vm.errorMessage)
        }
    }
}