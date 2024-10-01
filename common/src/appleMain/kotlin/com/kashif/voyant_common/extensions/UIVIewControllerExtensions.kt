package com.kashif.voyant_common.extensions


import platform.Foundation.NSLog
import platform.UIKit.UIApplication
import platform.UIKit.UINavigationController
import platform.UIKit.UITabBarController
import platform.UIKit.UIViewController
import platform.UIKit.childViewControllers
import platform.UIKit.navigationController

/**
 * Retrieves the top `UIViewController` from the view hierarchy.
 *
 * @param base The base `UIViewController` to start the search from. Defaults to the root view controller of the key window.
 * @return The top `UIViewController`, or null if none is found.
 */
fun getTopViewController(base: UIViewController? = UIApplication.sharedApplication().keyWindow?.rootViewController): UIViewController? {
    when {
        base is UINavigationController -> {
            return getTopViewController(base = base.visibleViewController)
        }

        base is UITabBarController -> {
            return getTopViewController(base = base.selectedViewController)
        }

        base?.presentedViewController != null -> {
            return getTopViewController(base = base.presentedViewController)
        }

        base.toString().contains("HostingController") -> return getTopViewController(
            base = base?.childViewControllers()?.first() as UIViewController
        )

        else -> {
            return base
        }
    }
}

/**
 * Logs the hierarchy of the top `UIViewController` for debugging purposes.
 *
 * @param base The base `UIViewController` to start the search from. Defaults to the root view controller of the key window.
 */
fun debugTopViewController(base: UIViewController? = UIApplication.sharedApplication().keyWindow?.rootViewController) {
    if (base is UINavigationController) {
        NSLog("TopViewController: UINavigationController with visible view controller: ${base.visibleViewController}")
        debugTopViewController(base = base.visibleViewController)
    } else if (base is UITabBarController) {
        NSLog("TopViewController: UITabBarController with selected view controller: ${base.selectedViewController}")
        debugTopViewController(base = base.selectedViewController)
    } else if (base?.presentedViewController != null) {
        NSLog("TopViewController: Presented view controller: ${base.presentedViewController}")
        debugTopViewController(base = base.presentedViewController)
    } else {
        NSLog("TopViewController: ${base}")
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
