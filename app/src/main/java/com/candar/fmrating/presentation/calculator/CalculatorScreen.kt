package com.candar.fmrating.presentation.calculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.candar.fmrating.presentation.calculator.components.AttributeInputList
import com.candar.fmrating.presentation.calculator.components.RatingText
import com.candar.fmrating.presentation.calculator.components.SaveDialog
import com.candar.fmrating.presentation.calculator.components.SavedRowItem

@Composable
fun CalculatorScreen(viewModel: CalculatorViewModel = viewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val attributesList = state.attributes.toList()

    var showSaveDialog by rememberSaveable { mutableStateOf(false) }

    Scaffold(contentWindowInsets = WindowInsets.safeContent) { innerPadding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(4.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            RatingText(
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                value = "%.1f".format(state.rating)
            )

            AttributeInputList(
                items = attributesList,
                onValueChange = { id, value -> viewModel.changeValue(id, value) })

            Button(
                onClick = { showSaveDialog = true },
                content = { Text("Save") },
                modifier = Modifier.fillMaxWidth()
            )

            if (state.savedPlayers.isNotEmpty()) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    state.savedPlayers.forEach {
                        SavedRowItem(item = it, onDelete = { viewModel.deletePlayer(it.id) })
                    }
                }
            }

            if (showSaveDialog) {
                SaveDialog(
                    onDismissRequest = { showSaveDialog = false },
                    onSave = { viewModel.savePlayer(it) })
            }
        }
    }
}