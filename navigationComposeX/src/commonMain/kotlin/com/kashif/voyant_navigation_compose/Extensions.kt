package com.kashif.voyant_navigation_compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavController


interface VoyantRoute{
    @Composable
    fun content(navController: NavController)
}


interface NavigationController {
    fun navigate(route: VoyantRoute)
    fun popBackStack()

}

expect fun NavController.navigateX(route: VoyantRoute)

expect fun NavController.popBackStackX()