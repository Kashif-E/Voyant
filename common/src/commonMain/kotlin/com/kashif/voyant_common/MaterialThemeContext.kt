package com.kashif.voyant_common

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 * Manages the current AppTheme composable function that wraps content.
 */
object ThemeManager {
    /**
     * Holds the current theme composable function that accepts content
     *
     **/
    var currentTheme by mutableStateOf<@Composable (content: @Composable () -> Unit) -> Unit>({ content -> MaterialTheme { content() } })
        private set

    /**
     * Updates the current theme with a new theme composable function that accepts content.
     *
     * @param newTheme The new theme composable to apply, which accepts content.
     */
    fun setTheme(newTheme: @Composable (content: @Composable () -> Unit) -> Unit) {
        currentTheme = newTheme
    }
}