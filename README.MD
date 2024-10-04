
# Voyant

<p align="center">
  <a href="https://github.com/Kashif-E/Voyant/releases/tag/0.0.1">
    <img src="https://img.shields.io/github/v/release/kashif-e/voyant" alt="Version">
  </a>
</p>

## Overview


 **Voyant** is an extension library for **Voyager** and **Jetpack Compose Navigation** it enables developers to use native navigation for **Apple** platforms.


## Installation

- For Voyager

```Kotlin  
implementation("io.github.kashif-mehmood-km:voyant-voyagerx:0.0.2")  
```  
- For compose Navigation

```Kotlin
implementation("io.github.kashif-mehmood-km:voyant-navigation-compose:0.0.2") 
```


## Supported Platforms

- Android
- IOS
- WasmJS
- JVM

## Usage

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
