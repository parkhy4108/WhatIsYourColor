package com.devstitch.whatsyourcolor.common.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.devstitch.whatsyourcolor.presentation.colorList.ColorListScreen
import com.devstitch.whatsyourcolor.presentation.myPalette.MyPaletteScreen
import com.devstitch.whatsyourcolor.presentation.season.SeasonScreen
import com.devstitch.whatsyourcolor.presentation.tone.ToneScreen

fun NavGraphBuilder.graph(navController: NavHostController, snackBarHostState: SnackbarHostState) {

    composable(
        route = Screen.SeasonScreen.route
    ) {
        SeasonScreen(openScreen = { route -> navController.navigate(route) })
    }
    composable(
        route = Screen.ToneScreen.route,
        arguments = listOf(navArgument(CHOSEN_SEASON) {
            type = NavType.StringType
        })
    ) {
        val season = it.arguments?.getString(CHOSEN_SEASON) ?: ""
        ToneScreen(
            season = season,
            openScreen = { route -> navController.navigate(route) },
            popUp = { navController.popBackStack() })
    }
    composable(
        route = Screen.ColorScreen.route,
        arguments = listOf(
            navArgument(CHOSEN_SEASON) {
                type = NavType.StringType
            },
            navArgument(CHOSEN_TONE) {
                type = NavType.StringType
            }
        )
    ) {
        val season = it.arguments?.getString(CHOSEN_SEASON) ?: ""
        val tone = it.arguments?.getString(CHOSEN_TONE) ?: ""
        ColorListScreen(
            season = season,
            tone = tone,
            snackBarHostState = snackBarHostState,
            openScreen = { route -> navController.navigate(route) },
            popUpScreen = { navController.popBackStack() }
        )
    }
    composable(route = Screen.MyPaletteScreen.route) {
        MyPaletteScreen(
            popUpScreen = { navController.popBackStack() }
        )
    }
}