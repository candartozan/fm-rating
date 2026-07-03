package com.candar.fmrating.calculator

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

class CalculatorViewModel : ViewModel() {
    private val _state = MutableStateFlow(CalculatorState())
    val state = _state.asStateFlow()

    fun changeValue(id: Int, value: Float) {
        val newAttributes = _state.value.attributes.toMutableSet().map {
            if (it.id == id) {
                it.copy(value = value.coerceIn(0f, 20f))
            } else it
        }.toSet()

        val newRating = calculateRating(newAttributes)
        _state.update { it.copy(attributes = newAttributes, rating = newRating) }
    }

    private fun calculateRating(attributes: Set<PlayerAttribute>): Float {
        var totalScore = 0f
        var totalWeight = 0f

        for (attribute in attributes) {
            val attributeValue = attribute.value

            totalScore += attributeValue * attribute.weight
            totalWeight += attribute.weight
        }

        return if (totalWeight > 0) {
            totalScore / totalWeight
        } else {
            0f
        }
    }

    fun savePlayer(name: String) {
        _state.update {
            it.copy(
                savedPlayers = it.savedPlayers.toMutableList() + listOf(
                    SavedPlayer(
                        id = UUID.randomUUID().toString(), name = name, rating = _state.value.rating
                    )
                )
            )
        }
    }

    fun deletePlayer(id: String) {
        _state.update { it.copy(savedPlayers = it.savedPlayers.filter { player -> player.id != id }) }
    }
}