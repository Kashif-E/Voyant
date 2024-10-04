package com.kashif.voyant_navigation_compose

import androidx.compose.runtime.Composable

@Composable
expect fun rememberKMPNavController(): NavigationController

interface VoyantRoute{
    @Composable
    fun content()
}


interface NavigationController {
    fun navigate(route: VoyantRoute)
    fun popBackStack()

}


