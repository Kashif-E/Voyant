package com.kashif.voyant


import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import com.kashif.voyant.extensions.extendedComposeViewController
import com.kashif.voyant_common.extensions.getNavigationController
import com.kashif.voyant_common.extensions.getTopViewController
import platform.Foundation.NSLog
import platform.UIKit.hidesBottomBarWhenPushed

/**
 * Pushes a new screen onto the navigation stack.
 *
 * @param screen The screen to be pushed.
 */
actual fun Navigator.pushX(screen: Screen, ) {
    val viewController = extendedComposeViewController(screen = screen)
    viewController.hidesBottomBarWhenPushed = true
    val navigationController = getNavigationController()
    navigationController?.pushViewController(viewController, animated = true) ?: run {
        NSLog("NavigationController is null")
    }
}

/**
 * Pops the top screen from the navigation stack.
 */
actual fun Navigator.popX() {
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

/**
 * Pops all the screens on the navigation stack until the root screen is at the top.
 */
actual fun Navigator.popUntilRootX() {
    val navigationController = getNavigationController()
    navigationController?.popToRootViewControllerAnimated(true) ?: run {
        NSLog("NavigationController is null")
    }
}


actual fun BottomSheetNavigator.hideX() {
    val topVc = getTopViewController()
    topVc?.dismissViewControllerAnimated(true, null) ?: run {
        NSLog("TopViewController is null")
    }
}

actual fun BottomSheetNavigator.showX(screen: Screen) {
    val viewController = extendedComposeViewController(screen = screen)
    val topVc = getTopViewController()
    topVc?.presentViewController(viewController, animated = true, completion = null) ?: run {
        NSLog("TopViewController is null")
    }
}


