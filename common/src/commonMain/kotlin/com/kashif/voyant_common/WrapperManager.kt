package com.kashif.voyant_common

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue

/**
 * WrapperManager is responsible for managing a composable wrapper function (such as a theme).
 * It allows setting an initial theme or wrapper and reapplying it across the app as needed.
 */
object WrapperManager {

    /**
     * savedLambda is a mutable state that holds the saved composable wrapper lambda passed by the user.
     */
    private var savedLambda by mutableStateOf<@Composable (content: @Composable () -> Unit) -> Unit>(
        { content -> MaterialTheme { content() } }
    )


    /**
     * Updates the current theme or wrapper with a new composable lambda.
     *
     * @param newLambda The new theme composable lambda that wraps content.
     */
    fun setLambda(newLambda: @Composable (content: @Composable () -> Unit) -> Unit) {
        savedLambda = newLambda
    }

    /**
     * Applies the saved theme or wrapper to the provided composable content.
     * If no saved theme is available, the current theme will be applied.
     *
     * @param content The composable content to wrap with the theme.
     */
    @Composable
    fun applyLambda(content: @Composable () -> Unit) {
        savedLambda(content)
    }
}

/**
 * Voyant is a composable function that sets up and applies a composable wrapper (such as a theme, koinApplication or a box with insets)
 * to the given content. The wrapper is set only once, after which it is reused automatically.
 *
 * @param content The composable content to display within the theme/Wrapper.
 * @param wrapper The composable wrapper function (e.g., theme) that applies styling or layout.
 */
@Composable
fun Voyant(
    wrapper: @Composable (@Composable () -> Unit) -> Unit,
    content: @Composable () -> Unit
) {
    val currentWrapper by rememberUpdatedState(wrapper)

    LaunchedEffect(Unit) {
        WrapperManager.setLambda(currentWrapper)
    }

    WrapperManager.applyLambda {
        content()
    }
}