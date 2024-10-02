package com.kashif.sample

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.kashif.sample.theme.AppTheme
import com.kashif.sample.theme.LocalThemeIsDark
import com.kashif.voyant.hideX
import com.kashif.voyant.popUntilRootX
import com.kashif.voyant.popX
import com.kashif.voyant.pushX
import com.kashif.voyant.showX
import com.kashif.voyant_navigation_compose.VoyantRoute
import com.kashif.voyant_navigation_compose.navigateX
import com.kashif.voyant_navigation_compose.popBackStackX
import kotlinx.coroutines.isActive
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import voyant.composeapp.generated.resources.IndieFlower_Regular
import voyant.composeapp.generated.resources.Res
import voyant.composeapp.generated.resources.cyclone
import voyant.composeapp.generated.resources.ic_cyclone
import voyant.composeapp.generated.resources.ic_dark_mode
import voyant.composeapp.generated.resources.ic_light_mode
import voyant.composeapp.generated.resources.ic_rotate_right
import voyant.composeapp.generated.resources.open_github
import voyant.composeapp.generated.resources.run
import voyant.composeapp.generated.resources.stop
import voyant.composeapp.generated.resources.theme

@Composable
internal fun App() = AppTheme {
    // call the function that contains the navigation you want to test
    VoyagerNavigation()
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun VoyagerNavigation() {
    BottomSheetNavigator {
        Navigator(ScreenA())
    }
}

@Composable
fun NavigationCompose() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MovieScreenRoute) {
        composable<MovieScreenRoute> { backStackEntry ->
            val searchDomainModel = backStackEntry.toRoute<MovieScreenRoute>()
            searchDomainModel.content()
        }
        composable<MovieDetailsScreen> { navBackStackEntry ->
            val searchDomainModel = navBackStackEntry.toRoute<MovieDetailsScreen>()
            searchDomainModel.content()
        }
    }
}


@Serializable
object MovieScreenRoute : VoyantRoute {
    @Composable
    override fun content() {
        val navController = rememberNavController()
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                "Movie Screen",
                modifier = Modifier.padding(16.dp).align(Alignment.Center).clickable {
                    navController.navigateX(MovieDetailsScreen)
                })
        }
    }
}

@Serializable
object MovieDetailsScreen : VoyantRoute {
    @Composable
    override fun content() {
        val navController = rememberNavController()
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                "Movie details Screen",
                modifier = Modifier.padding(16.dp).align(Alignment.Center).clickable {
                    navController.popBackStackX()
                })
        }
    }
}


class ScreenA : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeDrawing)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(Res.string.cyclone),
                fontFamily = FontFamily(Font(Res.font.IndieFlower_Regular)),
                style = MaterialTheme.typography.displayLarge
            )

            var isRotating by remember { mutableStateOf(false) }

            val rotate = remember { Animatable(0f) }
            val target = 360f
            if (isRotating) {
                LaunchedEffect(Unit) {
                    while (isActive) {
                        val remaining = (target - rotate.value) / target
                        rotate.animateTo(
                            target,
                            animationSpec = tween(
                                (1_000 * remaining).toInt(),
                                easing = LinearEasing
                            )
                        )
                        rotate.snapTo(0f)
                    }
                }
            }

            Image(
                modifier = Modifier
                    .size(250.dp)
                    .padding(16.dp)
                    .run { rotate(rotate.value) },
                imageVector = vectorResource(Res.drawable.ic_cyclone),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
                contentDescription = null
            )

            ElevatedButton(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .widthIn(min = 200.dp),
                onClick = { isRotating = !isRotating },
                content = {
                    Icon(vectorResource(Res.drawable.ic_rotate_right), contentDescription = null)
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text(
                        stringResource(if (isRotating) Res.string.stop else Res.string.run)
                    )
                }
            )

            var isDark by LocalThemeIsDark.current
            val icon = remember(isDark) {
                if (isDark) Res.drawable.ic_light_mode
                else Res.drawable.ic_dark_mode
            }

            ElevatedButton(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    .widthIn(min = 200.dp),
                onClick = { isDark = !isDark },
                content = {
                    Icon(vectorResource(icon), contentDescription = null)
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text(stringResource(Res.string.theme))
                }
            )

            val uriHandler = LocalUriHandler.current
            TextButton(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    .widthIn(min = 200.dp),
                onClick = { uriHandler.openUri("https://github.com/terrakok") },
            ) {
                Text(stringResource(Res.string.open_github))
            }

            Button(
                onClick = { navigator.pushX(ScreenB()) },
                content = { Text("Go to Screen B") }
            )
        }
    }
}

class ScreenB : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text ="Screen B",
                style = MaterialTheme.typography.headlineSmall
            )
            Button(
                onClick = { navigator.pushX(screen = ScreenC()) },
                content = { Text("Go to Screen C") }
            )
        }
    }
}

class ScreenC : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val bottomSheetNavigator = LocalBottomSheetNavigator.current
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text ="Screen C",
                style = MaterialTheme.typography.headlineSmall
            )
            Button(
                onClick = { navigator.popUntilRootX()},
                content = { Text("Go to Screen A - pop to root") }
            )

            Button(
                onClick = { navigator.popX() },
                content = { Text("pop") }
            )

            Button(
                onClick = { bottomSheetNavigator.showX(SampleBottomSheet()) },
                content = { Text("show bottom sheet") }
            )
        }
    }
}

class SampleBottomSheet : Screen {
    @Composable
    override fun Content() {

        val bottomSheetNavigator = LocalBottomSheetNavigator.current
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Bottom Sheet",
                style = MaterialTheme.typography.headlineSmall
            )
            Button(
                onClick = { bottomSheetNavigator.hideX() },
                content = { Text("Close") }
            )
        }
    }
}