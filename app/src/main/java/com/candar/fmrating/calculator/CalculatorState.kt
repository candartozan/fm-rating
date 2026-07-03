package com.candar.fmrating.calculator

data class CalculatorState(
    val rating: Float = 0f, val attributes: Set<PlayerAttribute> = setOf(
        PlayerAttribute(id = 1, displayName = "Pace", value = 0f, weight = 64f),
        PlayerAttribute(id = 2, displayName = "Acceleration", value = 0f, weight = 64f),
        PlayerAttribute(id = 3, displayName = "Stamina", value = 0f, weight = 19f),
        PlayerAttribute(id = 4, displayName = "Dribbling", value = 0f, weight = 19f),
        PlayerAttribute(id = 5, displayName = "Anticipation", value = 0f, weight = 18f),
        PlayerAttribute(id = 6, displayName = "Jumping Reach", value = 0f, weight = 16f),
        PlayerAttribute(id = 7, displayName = "Balance", value = 0f, weight = 13f),
        PlayerAttribute(id = 8, displayName = "Work Rate", value = 0f, weight = 12f),
    ), val savedPlayers: List<SavedPlayer> = emptyList()
)

data class PlayerAttribute(
    val id: Int, val displayName: String, val value: Float, val weight: Float
)

data class SavedPlayer(val id: String, val name: String, val rating: Float)