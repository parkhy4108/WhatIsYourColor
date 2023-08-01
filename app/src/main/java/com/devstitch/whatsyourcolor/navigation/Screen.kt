package com.devstitch.whatsyourcolor.navigation

const val CHOSEN_SEASON = "season"
const val CHOSEN_TONE = "tone"

sealed class Screen(val route: String) {
    object SeasonScreen : Screen(route = "SEASON")
    object ToneScreen: Screen(route = "TONE/{$CHOSEN_SEASON}") {
        fun passSeason(id: String): String = this.route.replace(oldValue = CHOSEN_SEASON, newValue = id)
    }
    object ColorScreen: Screen(route = "COLOR/{$CHOSEN_TONE") {
        fun passTone(id: String): String = this.route.replace(oldValue = "{$CHOSEN_TONE}", newValue = id)
    }
    object MyColor: Screen(route = "MyCOLOR")
}
