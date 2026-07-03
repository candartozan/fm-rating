package com.candar.fmrating.calculator

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

class CalculatorViewModel : ViewModel() {
    private val _state = MutableStateFlow(CalculatorState())
    val state = _state.asStateFlow()

    fun changeValue(id: Int, value: String) {
        val finalValue = if (value.isEmpty()) ""
        else value.toIntOrNull()?.coerceIn(0, 20)?.toString() ?: return

        val newAttributes = _state.value.attributes.toMutableSet().map {
            if (it.id == id) {
                it.copy(value = finalValue)
            } else it
        }.toSet()

        val newRating = calculateRating(newAttributes)
        _state.update { it.copy(attributes = newAttributes, rating = newRating) }
    }

    private fun calculateRating(attributes: Set<PlayerAttribute>): Double {
        var totalScore = 0.0
        var totalWeight = 0.0

        for (attribute in attributes) {
            val attributeValue = attribute.value.toDoubleOrNull() ?: 0.0

            totalScore += attributeValue * attribute.weight
            totalWeight += attribute.weight
        }

        return if (totalWeight > 0) {
            totalScore / totalWeight
        } else {
            0.0
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

    fun movePlayer(fromIndex: Int, toIndex: Int) {
        _state.update { currentState ->
            val updatedList = currentState.savedPlayers.toMutableList()

            if (fromIndex in 0..updatedList.lastIndex && toIndex in 0..updatedList.lastIndex) {
                val item = updatedList.removeAt(fromIndex)
                updatedList.add(toIndex, item)
                currentState.copy(savedPlayers = updatedList)
            } else {
                currentState
            }
        }
    }
}