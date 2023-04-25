package com.example.todo.uilayer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.todo.datalayer.ToDo
import com.example.todo.navigation.Routes

@Composable
fun HomeScreen(toDoViewModel: ToDoViewModel = viewModel(), navController: NavHostController) {

    val toDoList by toDoViewModel.toDoState.collectAsState()

    LazyColumn {
        items(toDoList) { myToDo ->
            ToDoCard(toDoItems = myToDo, navController = navController)
        }
    }

}


@Composable
fun ToDoCard(toDoItems: ToDo, navController: NavHostController) {
    Card(
        modifier = Modifier.clickable(onClick = { navController.navigate(Routes.Edit.name) }),
        elevation = CardDefaults.cardElevation(7.dp)
    ) {
        Column(
            modifier = Modifier.padding(7.dp),
            verticalArrangement = Arrangement.spacedBy(3.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Title: ${toDoItems.title}")
            Spacer(modifier = Modifier.height(3.dp))
            Text(text = "Description: ${toDoItems.description}")
            Spacer(modifier = Modifier.height(3.dp))
        }
    }
}