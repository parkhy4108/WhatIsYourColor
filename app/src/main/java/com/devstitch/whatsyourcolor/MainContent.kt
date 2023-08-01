package com.devstitch.whatsyourcolor

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.devstitch.whatsyourcolor.navigation.Screen
import com.devstitch.whatsyourcolor.navigation.graph
import com.devstitch.whatsyourcolor.ui.theme.WhatsYourColorTheme

@Composable
fun MainContent(){
    WhatsYourColorTheme {
        val navController = rememberNavController()
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NavHost(navController = navController, startDestination = Screen.SeasonScreen.route) {
                graph(navController)
            }
        }
    }
}
