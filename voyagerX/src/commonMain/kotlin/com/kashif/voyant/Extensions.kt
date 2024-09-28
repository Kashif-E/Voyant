package com.kashif.voyant

import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator

expect fun Navigator.popX()

expect fun Navigator.pushX(screen: Screen)

expect fun Navigator.popUntilRootX()

expect fun BottomSheetNavigator.hideX()

expect fun BottomSheetNavigator.showX(screen: Screen)