package com.example.native_task_catalift.presentation.navigation

sealed class Screen( val route: String) {
    object Interest : Screen("interest")
    object Profession : Screen("profession")
}