package com.kashif.sample

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kashif.sample.compose.NavigationCompose
import com.kashif.sample.theme.AppTheme
import com.kashif.sample.voyager.VoyagerNavigation

@Composable
internal fun App() = AppTheme {
    var useComposeNavigation by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Display the appropriate navigation based on the state
        if (useComposeNavigation) {
            NavigationCompose()
        } else {
            VoyagerNavigation()
        }

        NavigationToggleButton(
            isComposeNavigation = useComposeNavigation,
            onToggle = { useComposeNavigation = it }
        )
    }
}

/**
 * Composable for the toggle button to switch between Compose and Voyager navigation.
 * @param isComposeNavigation The current state of the toggle button (true for Compose, false for Voyager).
 * @param onToggle Callback invoked when the toggle state changes.
 */
@Composable
fun BoxScope.NavigationToggleButton(
    isComposeNavigation: Boolean,
    onToggle: (Boolean) -> Unit
) {
    Button(
        onClick = { onToggle(!isComposeNavigation) },
        modifier = Modifier
            .align(Alignment.CenterStart),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isComposeNavigation) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
        )
    ) {
        Text(
            text = if (isComposeNavigation) "Switch to Voyager" else "Switch to Compose",
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}