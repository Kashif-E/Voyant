package com.kashif.voyant_navigation_compose

import androidx.navigation.NavHostController

actual fun NavHostController.popBackStackX() {
    this.popBackStack()
}

actual fun NavHostController.navigateX(route: VoyantRoute) {
    this.navigate(route)
}