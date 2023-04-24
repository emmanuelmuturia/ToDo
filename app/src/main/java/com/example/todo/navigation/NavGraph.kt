package com.example.todo.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todo.uilayer.HomeScreen
import com.example.todo.uilayer.NewToDo
import com.example.todo.uilayer.ToDoApp
import com.example.todo.uilayer.ToDoViewModel

@Composable
fun ToDoNavHost(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Routes.Home.name) {
        composable(route = Routes.Home.name) {
            ToDoApp(navController = navController)
        }

        composable(route = Routes.Add.name) {
            NewToDo(navController = navController)
        }
    }

}