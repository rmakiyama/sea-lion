package com.rmakiyama.sealion

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rmakiyama.sealion.ui.addedittask.AddEditTaskScreen
import com.rmakiyama.sealion.ui.home.HomeScreen

@Composable
fun AppNavigator(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(onClickAddTask = {
                navController.navigate(Screen.AddTask.route)
            })
        }
        composable(Screen.AddTask.route) {
            AddEditTaskScreen(taskId = null, navigateUp = { navController.popBackStack() })
        }
        composable(Screen.EditTask.route) { backStackEntry ->
            AddEditTaskScreen(
                taskId = requireNotNull(backStackEntry.arguments?.getString("taskId")),
                navigateUp = { navController.popBackStack() },
            )
        }
    }
}

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object AddTask : Screen("addtask")
    object EditTask : Screen("edittask/{taskId}") {
        fun createRoute(taskId: String): String = "edittask/${taskId}"
    }
}
