package com.kashif.voyant_common.extensions

import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCAction
import kotlinx.cinterop.useContents
import platform.CoreGraphics.CGFloat
import platform.CoreGraphics.CGPoint
import platform.CoreGraphics.CGRect
import platform.CoreGraphics.CGRectMake
import platform.Foundation.NSLog
import platform.UIKit.UIColor
import platform.UIKit.UIEvent
import platform.UIKit.UIGestureRecognizer
import platform.UIKit.UIGestureRecognizerDelegateProtocol
import platform.UIKit.UINavigationController
import platform.UIKit.UINavigationControllerDelegateProtocol
import platform.UIKit.UISwipeGestureRecognizer
import platform.UIKit.UIView
import platform.UIKit.UIViewAutoresizingFlexibleHeight
import platform.UIKit.UIViewAutoresizingFlexibleWidth
import platform.UIKit.UIViewController
import platform.UIKit.addChildViewController
import platform.UIKit.navigationController
import platform.UIKit.navigationItem
import platform.UIKit.willMoveToParentViewController

class UIViewControllerWrapper(
    private val controller: UIViewController,
) : UIViewController(null, null), UIGestureRecognizerDelegateProtocol,
    UINavigationControllerDelegateProtocol {

    @OptIn(ExperimentalForeignApi::class)
    override fun loadView() {
        super.loadView()

        controller.willMoveToParentViewController(this)
        controller.view.setFrame(view.bounds)
        view.addSubview(controller.view)
        addChildViewController(controller)

        createPaddingViews()
    }



    @OptIn(ExperimentalForeignApi::class)
    private fun createPaddingViews() {
        val padding: CGFloat = 20.0

        val width = view.bounds.useContents { this.size.width }
        val height = view.bounds.useContents { this.size.height }
        val topPaddingView = UIView(CGRectMake(0.0, 0.0, width, padding)).apply {
            backgroundColor = UIColor.clearColor
            autoresizingMask = UIViewAutoresizingFlexibleWidth
        }

        val bottomPaddingView = UIView(
            CGRectMake(
                0.0,
                height - padding,
                width,
                padding
            )
        ).apply {
            backgroundColor = UIColor.clearColor
            autoresizingMask = UIViewAutoresizingFlexibleWidth
        }

        val leftPaddingView =
            UIView(CGRectMake(0.0, padding, padding, height - 2 * padding)).apply {
                backgroundColor = UIColor.clearColor
                autoresizingMask = UIViewAutoresizingFlexibleHeight
            }

        val rightPaddingView = UIView(
            CGRectMake(
                width - padding,
                padding,
                padding,
                height - 2 * padding
            )
        ).apply {
            backgroundColor = UIColor.clearColor
            autoresizingMask = UIViewAutoresizingFlexibleHeight
        }

        view.addSubview(topPaddingView)
        view.addSubview(bottomPaddingView)
        view.addSubview(leftPaddingView)
        view.addSubview(rightPaddingView)
    }

    override fun viewDidLoad() {
        super.viewDidLoad()
        navigationController?.interactivePopGestureRecognizer?.enabled= true
        navigationController?.interactivePopGestureRecognizer?.delegate = this
    }


    override fun viewWillAppear(animated: Boolean) {
        super.viewWillAppear(animated)
        navigationController?.interactivePopGestureRecognizer?.delegate = this
        navigationItem.hidesBackButton = true
    }


    @OptIn(BetaInteropApi::class)
    @ObjCAction
    fun handleSwipe(sender: UISwipeGestureRecognizer) {
        NSLog("Swipe detected: ${sender.direction}")
    }

    override fun navigationController(
        navigationController: UINavigationController,
        willShowViewController: UIViewController,
        animated: Boolean
    ) {
        navigationController.interactivePopGestureRecognizer?.enabled = true
    }

    override fun gestureRecognizerShouldBegin(gestureRecognizer: UIGestureRecognizer): Boolean {
        return true
    }

    override fun gestureRecognizer(
        gestureRecognizer: UIGestureRecognizer,
        shouldRecognizeSimultaneouslyWithGestureRecognizer: UIGestureRecognizer
    ): Boolean {
        return true
    }
}

@OptIn(ExperimentalForeignApi::class)
class TransparentPassThroughView(frame: CValue<CGRect>) : UIView(frame = frame),
    UIGestureRecognizerDelegateProtocol {

    init {
        backgroundColor = UIColor.clearColor
    }

    override fun hitTest(point: CValue<CGPoint>, withEvent: UIEvent?): UIView? {

        val pointX = point.useContents { this.x }
        val pointY = point.useContents { this.y }

        if (pointX >= 0 && pointX <= bounds.useContents { this.size.width } && pointY >= 0 && pointY <= bounds.useContents { this.size.height }) {
            return null
        }

        return super.hitTest(point, withEvent)
    }

    override fun gestureRecognizerShouldBegin(gestureRecognizer: UIGestureRecognizer): Boolean {
        NSLog("gestureRecognizerShouldBegin: true")
        return true // Allow gesture recognition to begin
    }

    override fun gestureRecognizer(
        gestureRecognizer: UIGestureRecognizer,
        shouldRecognizeSimultaneouslyWithGestureRecognizer: UIGestureRecognizer
    ): Boolean {
        return true
    }
}
