package com.devstitch.whatsyourcolor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.devstitch.whatsyourcolor.common.navigation.Screen
import com.devstitch.whatsyourcolor.common.navigation.graph
import com.devstitch.whatsyourcolor.common.theme.WhatsYourColorTheme
import com.devstitch.whatsyourcolor.presentation.GoogleAdView

@Composable
fun MainContent() {
    WhatsYourColorTheme {
        val navController = rememberNavController()
        val snackBarHostState = remember { SnackbarHostState() }
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                snackbarHost = {
                    SnackbarHost(
                        hostState = snackBarHostState,
                        modifier = Modifier,
                        snackbar = { snackBarData ->
                            Snackbar(snackBarData)
                        }
                    )
                }
            ) { paddingValues ->
                Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
                    GoogleAdView()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.SeasonScreen.route
                    ) {
                        graph(navController, snackBarHostState)
                    }
                }
            }

        }
    }
}
