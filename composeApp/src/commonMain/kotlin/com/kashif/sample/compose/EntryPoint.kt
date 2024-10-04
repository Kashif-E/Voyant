package com.kashif.sample.compose

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute


@Composable
fun NavigationCompose() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MovieScreenRoute) {
        composable<MovieScreenRoute> { backStackEntry ->
            val searchDomainModel = backStackEntry.toRoute<MovieScreenRoute>()
            searchDomainModel.content()
        }
        composable<MovieDetailsScreenRoute> { navBackStackEntry ->
            val movieDetailsScreenRoute = navBackStackEntry.toRoute<MovieDetailsScreenRoute>()
            movieDetailsScreenRoute.content()
        }
    }
}
