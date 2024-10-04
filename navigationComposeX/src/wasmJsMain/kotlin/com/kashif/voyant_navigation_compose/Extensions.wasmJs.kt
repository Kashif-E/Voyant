package com.kashif.voyant_navigation_compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class AndroidNavController(private val navController: NavController) : NavigationController {
    override fun navigate(route: VoyantRoute) {
        navController.navigate(route)
    }

    override fun popBackStack() {
        navController.popBackStack()
    }
}

@Composable
actual fun  rememberKMPNavController(): NavigationController {
    val navController = rememberNavController()
    return AndroidNavController(navController)
}