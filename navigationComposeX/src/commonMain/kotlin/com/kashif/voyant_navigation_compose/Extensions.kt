package com.kashif.voyant_navigation_compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController



expect fun NavHostController.popBackStackX()

expect fun NavHostController.navigateX(route: VoyantRoute)

interface VoyantRoute{
    @Composable
    fun content()
}