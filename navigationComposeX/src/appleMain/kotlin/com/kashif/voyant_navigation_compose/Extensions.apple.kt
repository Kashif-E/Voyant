package com.kashif.voyant_navigation_compose

import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.ui.uikit.OnFocusBehavior
import androidx.compose.ui.window.ComposeUIViewController
import androidx.navigation.NavController
import com.kashif.voyant_common.WrapperManager
import com.kashif.voyant_common.extensions.UIViewControllerWrapper
import com.kashif.voyant_common.extensions.getNavigationController
import platform.Foundation.NSLog
import platform.UIKit.UIViewController
import platform.UIKit.hidesBottomBarWhenPushed


@OptIn(ExperimentalComposeApi::class)
fun extendedComposeViewController(
    screen: VoyantRoute,
    isOpaque: Boolean = true,
): UIViewController {

    val uiViewController = ComposeUIViewController(configure = {
        onFocusBehavior = OnFocusBehavior.DoNothing
        opaque = isOpaque
    }) {
        WrapperManager.applyLambda {
            screen.content(NavController())
        }
    }
    return UIViewControllerWrapper(uiViewController)
}


actual fun NavController.navigateX(route: VoyantRoute) {

    val viewController =
        extendedComposeViewController(screen = route)
    viewController.hidesBottomBarWhenPushed = true
    getNavigationController()?.pushViewController(viewController, animated = true) ?: run {
        NSLog("NavigationController is null, If you are using swift ui the read the instructions in readme.md")
    }
}

actual fun NavController.popBackStackX() {
    getNavigationController()?.let { navController ->
        if (navController.viewControllers.size > 1) {
            navController.popViewControllerAnimated(true)
        } else {
            NSLog("Cannot pop. Only one view controller in the stack.")
        }
    } ?: run {
        NSLog("NavigationController is null, If you are using swift ui the read the instructions in readme.md")
    }
}
