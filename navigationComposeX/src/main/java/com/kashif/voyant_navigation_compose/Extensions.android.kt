package com.kashif.voyant_navigation_compose

import androidx.navigation.NavController

actual fun NavController.navigateX(route: VoyantRoute) {
    navigate(route)
}

actual fun NavController.popBackStackX() {
    popBackStack()
}
