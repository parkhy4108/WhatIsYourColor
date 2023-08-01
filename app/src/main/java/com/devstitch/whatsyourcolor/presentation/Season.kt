package com.devstitch.whatsyourcolor.presentation

sealed interface Season {
    object Spring : Season
    object Summer : Season
    object Autumn : Season
    object Winter : Season
}