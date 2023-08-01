package com.devstitch.whatsyourcolor.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.devstitch.whatsyourcolor.presentation.season.SeasonScreen
import com.devstitch.whatsyourcolor.presentation.tone.ToneScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.graph(navController: NavHostController) {

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
        ToneScreen(season = season)
    }
    composable(
        route = Screen.ColorScreen.route,
        arguments = listOf(navArgument(CHOSEN_TONE) {
            type = NavType.StringType
        })
    ) {

    }
    composable(route = Screen.MyColor.route) {

    }
}