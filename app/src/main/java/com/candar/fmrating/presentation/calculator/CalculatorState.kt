package com.candar.fmrating.presentation.calculator

data class CalculatorState(
    val rating: Float = 0f, val attributes: Set<PlayerAttribute> = setOf(
        PlayerAttribute(id = 1, displayName = "Pace", value = 0, weight = 64),
        PlayerAttribute(id = 2, displayName = "Acceleration", value = 0, weight = 64),
        PlayerAttribute(id = 3, displayName = "Stamina", value = 0, weight = 19),
        PlayerAttribute(id = 4, displayName = "Dribbling", value = 0, weight = 19),
        PlayerAttribute(id = 5, displayName = "Anticipation", value = 0, weight = 18),
        PlayerAttribute(id = 6, displayName = "Jumping Reach", value = 0, weight = 16),
        PlayerAttribute(id = 7, displayName = "Balance", value = 0, weight = 13),
        PlayerAttribute(id = 8, displayName = "Work Rate", value = 0, weight = 12),
        PlayerAttribute(id = 9, displayName = "Strength", value = 0, weight = 10),
        PlayerAttribute(id = 10, displayName = "Concentration", value = 0, weight = 9),
        PlayerAttribute(id = 11, displayName = "Agility", value = 0, weight = 8),
        PlayerAttribute(id = 12, displayName = "Finishing", value = 0, weight = 8),
    ), val savedPlayers: List<SavedPlayer> = emptyList()
)

data class PlayerAttribute(
    val id: Int, val displayName: String, val value: Int, val weight: Int
)

data class SavedPlayer(val id: String, val name: String, val rating: Float)