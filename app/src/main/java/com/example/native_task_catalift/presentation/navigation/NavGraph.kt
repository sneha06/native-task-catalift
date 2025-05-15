package com.example.native_task_catalift.presentation.navigation

import android.annotation.SuppressLint
import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.native_task_catalift.presentation.screen.interest.InterestScreen
import com.example.native_task_catalift.presentation.screen.profession.ProfessionScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@SuppressLint("ContextCastToActivity")
@Composable
fun AppNavGraph(navController: NavHostController) {
    val activity = LocalContext.current as? Activity
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    NavHost(navController = navController, startDestination = Screen.Profession.route) {
        composable(Screen.Interest.route) {
            InterestScreen(
                onContinue = { context ->
                    Toast.makeText(context, "Continue clicked!", Toast.LENGTH_SHORT).show()},
                onExit = { },
                onBack = {
                    backDispatcher?.onBackPressed()
                     }// pass context and finish activity
            )
        }

        composable(Screen.Profession.route) {
            ProfessionScreen(
                onBack = {
                    activity?.finishAffinity()
                         },
                onContinue = { context ->
                    navController.navigate(Screen.Interest.route)
                }
            )
        }
    }
}




