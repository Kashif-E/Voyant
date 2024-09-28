import UIKit
import ComposeApp

@main
class AppDelegate: UIResponder, UIApplicationDelegate {
    var window: UIWindow?

    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?
    ) -> Bool {
        window = UIWindow(frame: UIScreen.main.bounds)
        if let window = window {
           let uiController = UINavigationController( rootViewController: MainKt.MainViewController())
            uiController.interactivePopGestureRecognizer?.isEnabled = true
            window.rootViewController = uiController
           
            window.makeKeyAndVisible()
        }
        
        
        return true
    }
}
