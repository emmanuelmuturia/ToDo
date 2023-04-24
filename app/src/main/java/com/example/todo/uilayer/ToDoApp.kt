package com.example.todo.uilayer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.todo.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoApp(navController: NavHostController) {

    val toDoViewModel: ToDoViewModel = viewModel()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar(title = { Text("ToDo") }) },
        floatingActionButton = {
            Column {
                FloatingActionButton(onClick = {
                    toDoViewModel.deleteToDo()
                }) {
                    Icon(imageVector = Icons.Rounded.Delete,
                        contentDescription = "Delete FAB",
                        tint = Color.White,)
                }

                Spacer(modifier = Modifier.height(7.dp))

                /*FloatingActionButton(onClick = {
                    navController.navigate(Routes.Home.name)
                }) {
                    Icon(imageVector = Icons.Rounded.Refresh,
                        contentDescription = "Refresh FAB",
                        tint = Color.White,)
                }

                Spacer(modifier = Modifier.height(7.dp))*/

                FloatingActionButton(onClick = {
                    navController.navigate(Routes.Add.name)
                }) {
                    Icon(imageVector = Icons.Rounded.Add,
                        contentDescription = "Add FAB",
                        tint = Color.White,)
                }
            }
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreen(toDoViewModel = toDoViewModel)
        }
    }
}