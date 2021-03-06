package com.rmakiyama.sealion

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rmakiyama.sealion.domain.TaskId
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
            HomeScreen(
                onClickTask = { id -> navController.navigate(Screen.EditTask.createRoute(id)) },
                onClickAddTask = { navController.navigate(Screen.AddTask.route) }
            )
        }
        composable(Screen.AddTask.route) {
            AddEditTaskScreen(
                taskId = null,
                navigateUp = { navController.popBackStack() },
                onSaved = {
                    navController.popBackStack(
                        route = Screen.Home.route,
                        inclusive = false
                    )
                }
            )
        }
        composable(Screen.EditTask.route) { backStackEntry ->
            val taskId = TaskId(requireNotNull(backStackEntry.arguments?.getString("taskId")))
            AddEditTaskScreen(
                taskId = taskId,
                navigateUp = { navController.popBackStack() },
                onSaved = {
                    navController.popBackStack(
                        route = Screen.Home.route,
                        inclusive = false
                    )
                }
            )
        }
    }
}

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object AddTask : Screen("addtask")
    object EditTask : Screen("edittask/{taskId}") {
        fun createRoute(taskId: TaskId): String = "edittask/${taskId.id}"
    }
}
