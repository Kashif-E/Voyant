package com.kashif.voyant.extensions


import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCAction
import platform.Foundation.NSLog
import platform.Foundation.NSSelectorFromString
import platform.UIKit.UIEvent
import platform.UIKit.UIGestureRecognizer
import platform.UIKit.UIGestureRecognizerDelegateProtocol
import platform.UIKit.UINavigationController
import platform.UIKit.UINavigationControllerDelegateProtocol
import platform.UIKit.UIPress
import platform.UIKit.UISwipeGestureRecognizer
import platform.UIKit.UISwipeGestureRecognizerDirectionLeft
import platform.UIKit.UISwipeGestureRecognizerDirectionRight
import platform.UIKit.UITouch
import platform.UIKit.UIViewController
import platform.UIKit.addChildViewController
import platform.UIKit.didMoveToParentViewController
import platform.UIKit.navigationController
import platform.UIKit.willMoveToParentViewController

/**
 * A custom `UIViewController` that wraps another `UIViewController` and adds gesture recognizer functionality.
 * Implements the `UIGestureRecognizerDelegateProtocol` to handle swipe gestures.
 *
 * @property controller The `UIViewController` instance that is being wrapped.
 */
class UIViewControllerWrapper(
    private val controller: UIViewController,
) : UIViewController(null, null), UIGestureRecognizerDelegateProtocol,
    UINavigationControllerDelegateProtocol {

    /**
     * Called when the view controller's view is loaded into memory.
     * Sets up the view hierarchy by adding the wrapped controller's view as a subview
     * and managing the parent-child relationship between the view controllers.
     */
    @OptIn(ExperimentalForeignApi::class)
    override fun loadView() {
        super.loadView()
        controller.willMoveToParentViewController(this)
        controller.view.setFrame(view.frame)
        view.addSubview(controller.view)
        addChildViewController(controller)
        controller.didMoveToParentViewController(this)
    }

    /**
     * Called after the view has been loaded.
     * Sets the delegate for the interactive pop gesture recognizer and adds swipe gesture recognizers
     * for left and right swipe directions.
     */
    @OptIn(ExperimentalForeignApi::class)
    override fun viewDidLoad() {
        super.viewDidLoad()
        navigationController?.interactivePopGestureRecognizer?.delegate = this
    }

    /**
     * Handles the swipe gestures detected by the gesture recognizers.
     * Logs the direction of the swipe.
     *
     * @param sender The `UISwipeGestureRecognizer` that detected the swipe.
     */
    @OptIn(BetaInteropApi::class)
    @ObjCAction
    fun handleSwipe(sender: UISwipeGestureRecognizer) {
        NSLog("Swipe detected: ${sender.direction}")
    }


    /**
     * Determines whether the gesture recognizer should receive an object representing a touch.
     * Always returns `true`.
     *
     * @param gestureRecognizer The `UIGestureRecognizer` that is asking whether it should receive the touch.
     * @param shouldReceiveTouch The `UITouch` object representing the touch.
     * @return `true` to allow the gesture recognizer to receive the touch.
     */
    override fun gestureRecognizer(
        gestureRecognizer: UIGestureRecognizer,
        shouldReceiveEvent: UIEvent
    ): Boolean {
        println("gestureRecognizer shouldReceiveEvent")
        return true
    }

    /**
     *  This method is called for press events
     */
    override fun gestureRecognizer(
        gestureRecognizer: UIGestureRecognizer,
        shouldReceivePress: UIPress
    ): Boolean {
        println("gestureRecognizer shouldReceivePress")
        return true
    }


    override fun navigationController(
        navigationController: UINavigationController,
        willShowViewController: UIViewController,
        animated: Boolean
    ) {
        // Enable the gesture recognizer when pushing or popping view controllers
        navigationController.interactivePopGestureRecognizer?.setEnabled(navigationController.viewControllers.size > 1)
    }


    override fun gestureRecognizerShouldBegin(gestureRecognizer: UIGestureRecognizer): Boolean {
        println("gestureRecognizerShouldBegin")
        if (gestureRecognizer == navigationController?.interactivePopGestureRecognizer) {
            return navigationController?.viewControllers?.size ?: 0 > 1
        }
        return true
    }

    override fun gestureRecognizer(gestureRecognizer: UIGestureRecognizer, shouldReceiveTouch: UITouch): Boolean {
        println("gestureRecognizer shouldReceiveTouch")
        return true
    }

    override fun gestureRecognizer(
        gestureRecognizer: UIGestureRecognizer,
        shouldRequireFailureOfGestureRecognizer: UIGestureRecognizer
    ): Boolean {
        println("gestureRecognizer shouldRequireFailureOfGestureRecognizer")
        // Change this to return true for the navigation gesture
        return gestureRecognizer == navigationController?.interactivePopGestureRecognizer
    }

}