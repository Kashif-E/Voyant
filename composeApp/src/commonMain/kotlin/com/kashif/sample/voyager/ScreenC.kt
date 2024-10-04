package com.kashif.sample.voyager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.kashif.voyant.popUntilRootX
import com.kashif.voyant.popX
import com.kashif.voyant.showX

class ScreenC : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val bottomSheetNavigator = LocalBottomSheetNavigator.current
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text ="Screen C",
                style = MaterialTheme.typography.headlineSmall
            )
            Button(
                onClick = { navigator.popUntilRootX()},
                content = { Text("Go to Screen A - pop to root") }
            )

            Button(
                onClick = { navigator.popX() },
                content = { Text("pop") }
            )

            Button(
                onClick = { bottomSheetNavigator.showX(SampleBottomSheet()) },
                content = { Text("show bottom sheet") }
            )
        }
    }
}
