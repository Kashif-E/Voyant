package com.kashif.sample.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kashif.voyant_navigation_compose.VoyantRoute
import com.kashif.voyant_navigation_compose.rememberKMPNavController
import kotlinx.serialization.Serializable

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

@Serializable
object MovieDetailsScreenRoute : VoyantRoute {
    @Composable
    override fun content() {
        val navController = rememberKMPNavController()
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                "Movie details Screen",
                modifier = Modifier.padding(16.dp).align(Alignment.Center).clickable {
                    navController.popBackStack()
                })
        }
    }
}