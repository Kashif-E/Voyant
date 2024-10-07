package com.kashif.sample.compose

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.kashif.sample.theme.AppTheme
import com.kashif.voyant_common.Voyant


@Composable
fun NavigationCompose() {
    Voyant(content = {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = MovieScreenRoute) {
            composable<MovieScreenRoute> { backStackEntry ->
                val searchDomainModel = backStackEntry.toRoute<MovieScreenRoute>()
                searchDomainModel.content(navController)
            }
            composable<MovieDetailsScreenRoute> { navBackStackEntry ->
                val movieDetailsScreenRoute =
                    navBackStackEntry.toRoute<MovieDetailsScreenRoute>()
                movieDetailsScreenRoute.content(navController)
            }
        }

    }, wrapper = { content ->
        AppTheme {
            content()
        }
    })
}
