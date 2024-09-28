package com.kashif.voyant


import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import com.kashif.voyant.extensions.extendedComposeViewController
import com.kashif.voyant.extensions.getTopViewController
import platform.Foundation.NSLog
import platform.UIKit.UIGestureRecognizerDelegateProtocol
import platform.UIKit.UINavigationController
import platform.UIKit.UINavigationControllerDelegateProtocol
import platform.UIKit.hidesBottomBarWhenPushed
import platform.UIKit.navigationController

/**
 * Pushes a new screen onto the navigation stack.
 *
 * @param screen The screen to be pushed.
 */
actual fun Navigator.pushX(screen: Screen) {
    val viewController = extendedComposeViewController(screen = screen)
    viewController.hidesBottomBarWhenPushed = true

    val navigationController = getNavigationController()
    navigationController?.let { navController ->
        navController.pushViewController(viewController, animated = true)
        // Enable the gesture recognizer after pushing and set its delegate
        navController.interactivePopGestureRecognizer?.setEnabled(true)
        navController.interactivePopGestureRecognizer?.delegate = viewController as? UIGestureRecognizerDelegateProtocol
    } ?: run {
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

/**
 * Retrieves the top `UINavigationController` from the view hierarchy.
 *
 * @return The top `UINavigationController`, or null if none is found.
 */
fun getNavigationController(): UINavigationController? {
    val topVc = getTopViewController()
    return topVc?.let { topViewController ->
        topViewController as? UINavigationController ?: topViewController.navigationController
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