package com.example.todo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todo.uilayer.EditToDo
import com.example.todo.uilayer.NewToDo
import com.example.todo.uilayer.SearchScreen
import com.example.todo.uilayer.ToDoApp

@Composable
fun ToDoNavHost(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Routes.Home.name) {
        composable(route = Routes.Home.name) {
            ToDoApp(navController = navController)
        }

        composable(route = Routes.Add.name) {
            NewToDo(navController = navController)
        }

        composable(route = Routes.Edit.name.plus("?id={id}"), arguments = listOf(navArgument("id") {
            type = NavType.StringType
            nullable = true
        })) {
            EditToDo(navController = navController)
        }

        composable(route = Routes.Search.name) {
            SearchScreen(navController = navController)
        }
    }

}