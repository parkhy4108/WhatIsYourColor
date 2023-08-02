package com.devstitch.whatsyourcolor.navigation

const val CHOSEN_SEASON = "season"
const val CHOSEN_TONE = "tone"

sealed class Screen(val route: String) {
    object SeasonScreen : Screen(route = "SEASON")
    object ToneScreen : Screen(route = "TONE/{$CHOSEN_SEASON}") {
        fun passSeason(seasonStr: String): String =
            this.route.replace(oldValue = "{$CHOSEN_SEASON}", newValue = seasonStr)
    }

    object ColorScreen : Screen(route = "COLOR/{$CHOSEN_SEASON}/{$CHOSEN_TONE}") {
        fun passSeasonAndTone(seasonStr: String, toneStr: String): String = "COLOR/$seasonStr/$toneStr"
    }

    object MyColorScreen : Screen(route = "MyCOLOR")
}
