package com.kashif.voyant_navigation_compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.remember
import androidx.compose.ui.uikit.OnFocusBehavior
import androidx.compose.ui.window.ComposeUIViewController
import com.kashif.voyant_common.ThemeManager
import com.kashif.voyant_common.extensions.UIViewControllerWrapper
import com.kashif.voyant_common.extensions.getNavigationController
import platform.Foundation.NSLog
import platform.UIKit.UINavigationController
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
        ThemeManager.currentTheme {
            screen.content()
        }
    }
    return UIViewControllerWrapper(uiViewController)
}

class IOSNavController(
    private val navController: UINavigationController?,
) : NavigationController {
    override fun navigate(route: VoyantRoute) {
        val viewController =
            extendedComposeViewController(screen = route)
        viewController.hidesBottomBarWhenPushed = true
        navController?.pushViewController(viewController, animated = true) ?: run {
            NSLog("NavigationController is null")
        }
    }

    override fun popBackStack() {
        navController?.let { navController ->
            if (navController.viewControllers.size > 1) {
                navController.popViewControllerAnimated(true)
            } else {
                NSLog("Cannot pop. Only one view controller in the stack.")
            }
        } ?: run {
            NSLog("NavigationController is null")
        }
    }
}

@Composable
actual fun rememberKMPNavController(): NavigationController {
    val navController = getNavigationController()
    return remember(navController) {
        IOSNavController(navController)
    }
}

