package com.example.todo.uilayer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.todo.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoApp(navController: NavHostController = rememberNavController()) {

    val toDoViewModel: ToDoViewModel = viewModel()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row() {
                OutlinedTextField(value = toDoViewModel.toDoQuery, onValueChange = { toDoViewModel.toDoQuery = it }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done))
                Spacer(modifier = Modifier.width(3.dp))
                Button(onClick = {
                    navController.navigate(Routes.Search.name)
                    toDoViewModel.searchToDo(query = toDoViewModel.toDoQuery)
                }) {
                    Text(text = "Search")
                }
            }
        },
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
            HomeScreen(toDoViewModel = toDoViewModel, navController = navController)
        }
    }
}