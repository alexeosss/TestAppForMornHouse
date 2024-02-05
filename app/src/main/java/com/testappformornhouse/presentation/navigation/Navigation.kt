package com.testappformornhouse.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.testappformornhouse.presentation.screens.fact.FastScreen
import com.testappformornhouse.presentation.screens.main.MainScreen

enum class NavigationTree {
    MainScreen, FactScreen
}

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavigationTree.MainScreen.name) {
        composable(NavigationTree.MainScreen.name) {
            MainScreen(navController)
        }
        composable(NavigationTree.FactScreen.name + "/{numberFromScreen}") {
            FastScreen(navController)
        }

    }

}