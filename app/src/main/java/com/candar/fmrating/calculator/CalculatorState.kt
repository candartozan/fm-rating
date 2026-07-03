package com.candar.fmrating.calculator

data class CalculatorState(
    val rating: Double = 0.0, val attributes: Set<PlayerAttribute> = setOf(
        PlayerAttribute(id = 1, displayName = "Pace", value = "", weight = 64.0),
        PlayerAttribute(id = 2, displayName = "Acceleration", value = "", weight = 64.0),
        PlayerAttribute(id = 3, displayName = "Stamina", value = "", weight = 19.0),
        PlayerAttribute(id = 4, displayName = "Dribbling", value = "", weight = 19.0),
        PlayerAttribute(id = 5, displayName = "Anticipation", value = "", weight = 18.0),
        PlayerAttribute(id = 6, displayName = "Jumping Reach", value = "", weight = 16.0),
        PlayerAttribute(id = 7, displayName = "Balance", value = "", weight = 13.0),
        PlayerAttribute(id = 8, displayName = "Work Rate", value = "", weight = 12.0),
    ), val savedPlayers: List<SavedPlayer> = emptyList()
)

data class PlayerAttribute(
    val id: Int, val displayName: String, val value: String, val weight: Double
)

data class SavedPlayer(val id: String, val name: String, val rating: Double)