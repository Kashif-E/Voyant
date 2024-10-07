package com.kashif.sample.voyager

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import com.kashif.sample.theme.AppTheme
import com.kashif.voyant_common.Voyant


@Composable
@OptIn(ExperimentalMaterialApi::class)
fun VoyagerNavigation() {
    Voyant(content = {
        Navigator(ScreenA())
    }, wrapper = { content ->
        AppTheme {
            BottomSheetNavigator {
                content()
            }
        }
    })
}
