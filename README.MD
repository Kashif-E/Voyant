
# Voyant
<p align="left">
  <a href="https://github.com/Kashif-E/Voyant/releases/tag/0.0.1">
    <img src="https://img.shields.io/github/v/release/kashif-e/voyant" alt="Version">
  </a>
  <a href="https://mailchi.mp/kotlinweekly/kotlin-weekly-427">
    <img src="https://img.shields.io/badge/Kotlin%20Weekly-427-blue" alt="Kotlin Weekly">
  </a>
</p>

## Overview

 **Voyant** is an extension library for **Voyager** and **Jetpack Compose Navigation** it enables developers to use native navigation for **Apple** platforms.

## Demo

<img src="art/demo.gif" width="300" height="650" />

## Installation

- For Voyager

```Kotlin  
implementation("io.github.kashif-mehmood-km:voyant-voyagerx:0.0.5")  
```  
- For compose Navigation

```Kotlin
implementation("io.github.kashif-mehmood-km:voyant-navigation-compose:0.0.5") 
```

## Swift UI

- For Swift UI there is one small extra step

```Swift


struct NavigationControllerWrapper: UIViewControllerRepresentable {

    func makeUIViewController(context: Context) -> UINavigationController {
        // MainKt.MainViewController() is the entry point of the app wrapped inside a uinavigation controller
        //so you can get the navigation controller from the view controller
        let navigationController = UINavigationController(rootViewController: MainKt.MainViewController())
        navigationController.interactivePopGestureRecognizer?.isEnabled = true
        return navigationController
    }

    func updateUIViewController(_ uiViewController: UINavigationController, context: Context) {
    
    }
}
// content view being used inside AppDelegate to set the root view controller
struct ContentView: View {
    var body: some View {
        NavigationControllerWrapper()
            .edgesIgnoringSafeArea(.all)
    }
}



```


## Supported Platforms

- Android
- IOS
- WasmJS
- JVM

## Usage

**Theme**
IOS navigation works differently then compose navigation. In IOS navigation, we have to provide a
theme to every viewController as it can not inherit it from previous view controller. For that, we have to provide a theme to every viewcontroller.
Its done internally from the library. You only have to add this code to top level Composable.

```Kotlin
 LaunchedEffect(Unit) {
     //AppTheme Being your material theme
        ThemeManager.setTheme { content -> AppTheme { content() } }
    }
```

**Voyager**

Instead of using methods of voyager library like `push` use `pushX` instead. Similary for `BottomSheetNavigator` use `showX` instead of `show`

**Navigation Compose**

Inherit your screens from this `VoyantRoute` interface

```Kotlin
interface VoyantRoute{
    @Composable
    fun content()
}
```
create your screens like this

```kotlin
@Serializable
object MovieScreenRoute : VoyantRoute {
    @Composable
    override fun content() {
        val navController = rememberKMPNavController()
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                "Movie Screen",
                modifier = Modifier.padding(16.dp).align(Alignment.Center).clickable {
                    navController.navigate(MovieDetailsScreenRoute)
                })
        }
    }
}
```
- ***Note:*** Instead of `rememeberNavController` we are using `rememberKMPNavController` which is a wrapper around `NavHostController` to provide the same functionality as `NavHostController` for `Voyant` routes.


define the navgraph and use the serialized version of routes and get them from back stack entry

```Kotlin
@Composable
fun NavigationCompose() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MovieScreenRoute) {
        composable<MovieScreenRoute> { backStackEntry ->
            val searchDomainModel = backStackEntry.toRoute<MovieScreenRoute>()
            searchDomainModel.content()
        }
        composable<MovieDetailsScreenRoute> { navBackStackEntry ->
            val movieDetailsScreenRoute = navBackStackEntry.toRoute<MovieDetailsScreenRoute>()
            movieDetailsScreenRoute.content()
        }
    }
}


```



You can check the `composeApp` for more details on both `navigation compose` and `voyager` usage.

The library is in an experimental stage, APIs can change/break.


**Contributions**

We welcome contributions! Before submitting a pull request, please open an issue so we can discuss the proposed changes and collaborate on improving the project.

**Feature Requests**
Feature requests are encouraged, and I’ll do my best to address them as quickly as possible.


## License

```  
MIT License  
```
