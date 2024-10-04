package com.kashif.sample.voyager

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator


@Composable
@OptIn(ExperimentalMaterialApi::class)
fun VoyagerNavigation() {
    BottomSheetNavigator {
        Navigator(ScreenA())
    }
}


