package com.example.todo.uilayer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.todo.datalayer.ToDo
import com.example.todo.navigation.Routes

@Composable
fun NewToDo(navController: NavHostController) {

    val toDoViewModel: ToDoViewModel = viewModel()

    var toDoTitle by remember { mutableStateOf("") }
    var toDoDescription by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TheNewToDo(value = toDoTitle, onValueChanged = {toDoTitle = it}, label = { Text(text = "Enter title...") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next))
        TheNewToDo(value = toDoDescription, onValueChanged = {toDoDescription = it}, label = {
            Text(
                text = "Enter description..."
            )
        }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done))
        NewButton(onClick = {
            toDoViewModel.addToDo(toDo = ToDo(id = 0, title = toDoTitle, description = toDoDescription))
            navController.popBackStack(Routes.Home.name, inclusive = false)

        }, text = "Add")
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TheNewToDo(
    value: String,
    onValueChanged: (String) -> Unit,
    label: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions
) {
    OutlinedTextField(value = value, onValueChange = onValueChanged, label = label, keyboardOptions = keyboardOptions)
}


@Composable
fun NewButton(
    onClick: () -> Unit,
    text: String
) {
    Button(onClick = onClick) {
        Text(text = text)
    }
}