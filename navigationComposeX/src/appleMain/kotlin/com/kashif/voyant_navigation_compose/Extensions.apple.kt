package com.kashif.voyant_navigation_compose

import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.ui.uikit.OnFocusBehavior
import androidx.compose.ui.window.ComposeUIViewController
import androidx.navigation.NavHostController
import com.kashif.voyant_common.extensions.UIViewControllerWrapper
import com.kashif.voyant_common.extensions.getNavigationController
import platform.Foundation.NSLog
import platform.UIKit.UIViewController
import platform.UIKit.hidesBottomBarWhenPushed

actual fun NavHostController.popBackStackX() {
    val navigationController = getNavigationController()
    navigationController?.let { navController ->
        if (navController.viewControllers.size > 1) {
            navController.popViewControllerAnimated(true)
        } else {
            NSLog("Cannot pop. Only one view controller in the stack.")
        }
    } ?: run {
        NSLog("NavigationController is null")
    }
}


actual fun NavHostController.navigateX(route: VoyantRoute) {

    val viewController = extendedComposeViewController(screen = route)
    viewController.hidesBottomBarWhenPushed = true
    val navigationController = getNavigationController()
    navigationController?.pushViewController(viewController, animated = true) ?: run {
        NSLog("NavigationController is null")
    }
}


@OptIn(ExperimentalComposeApi::class)
fun extendedComposeViewController(
    screen: VoyantRoute,
    isOpaque: Boolean = true,
): UIViewController {

    val uiViewController = ComposeUIViewController(configure = {
        onFocusBehavior = OnFocusBehavior.DoNothing
        opaque = isOpaque
    }) {

        screen.content()
    }

    return UIViewControllerWrapper(uiViewController)
}