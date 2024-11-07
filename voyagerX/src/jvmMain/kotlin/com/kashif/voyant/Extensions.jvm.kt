package com.kashif.voyant

import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator

actual fun Navigator.popX() {
  pop()
}

actual fun Navigator.pushX(screen: Screen) {
  push(screen)
}

actual fun Navigator.popUntilRootX() {
  popUntilRoot()
}

actual fun BottomSheetNavigator.showX(
    screen: Screen,
    skipHalfExpanded: Boolean,
    fixedHeight: Double
) {
  show(screen)
}

actual fun BottomSheetNavigator.hideX() {
  hide()
}
