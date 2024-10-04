package com.kashif.sample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.kashif.sample.compose.NavigationCompose
import com.kashif.sample.theme.AppTheme
import com.kashif.sample.voyager.VoyagerNavigation
import com.kashif.voyant.pushX
import com.kashif.voyant_common.ThemeManager


@Composable
internal fun App() {

    LaunchedEffect(Unit) {
        ThemeManager.setTheme { content -> AppTheme { content() } }
    }

    AppTheme {
        Navigator(MainScreen())
    }
}

class MainScreen : Screen {
    @Composable
    override fun Content() {
        val localNavigator = LocalNavigator.currentOrThrow
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { localNavigator.pushX(VoyagerNav()) }) {
                Text(text = "Voyager Navigation")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { localNavigator.pushX(ComposeNav()) }) {
                Text(text = "Navigation Compose")
            }
        }
    }
}

class VoyagerNav : Screen {
    @Composable
    override fun Content() {
        VoyagerNavigation()
    }
}

class ComposeNav : Screen {
    @Composable
    override fun Content() {
        NavigationCompose()
    }
}









