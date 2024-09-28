package com.kashif.sample.voyager

import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator

actual fun Navigator.popX() {
    pop()
}

actual fun Navigator.popToRootX() {
    popUntilRoot()
}

actual fun Navigator.pushX(screen: Screen) {
    push(screen)
}

actual fun BottomSheetNavigator.hideX() {
    hide()
}

actual fun BottomSheetNavigator.showX(screen: Screen) {
    show(screen)
}