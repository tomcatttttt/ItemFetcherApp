package com.many.to.many.item.fetcher.app.presentation.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "items") {
        composable("items") {
            ItemScreen(navController = navController)
        }
        composable(
            route = "details/{itemId}/{itemName}/{itemImage}/{itemColor}",
            arguments = listOf(
                navArgument("itemId") { defaultValue = "" },
                navArgument("itemName") { defaultValue = "" },
                navArgument("itemImage") { defaultValue = "" },
                navArgument("itemColor") { defaultValue = "" }
            ),
            enterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(700))
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(700))
            }
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId") ?: ""
            val itemName = backStackEntry.arguments?.getString("itemName") ?: ""
            val itemImage = backStackEntry.arguments?.getString("itemImage") ?: ""
            val itemColor = backStackEntry.arguments?.getString("itemColor") ?: ""

            ItemDetailsScreen(
                itemId = itemId,
                itemName = itemName,
                itemImage = itemImage,
                itemColor = itemColor,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
