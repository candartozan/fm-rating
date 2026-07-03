package com.candar.fmrating.calculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.candar.fmrating.calculator.components.SaveDialog
import com.candar.fmrating.calculator.components.SavedRowItem
import com.candar.fmrating.ui.theme.Typography

@Composable
fun CalculatorScreen(viewModel: CalculatorViewModel = viewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val attributesList = state.attributes.toList()
    val focusManager = LocalFocusManager.current

    var showSaveDialog by rememberSaveable { mutableStateOf(false) }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(16.dp),
        ) {
            Text(
                text = "Current Rating: ${"%.1f".format(state.rating)}",
                style = Typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            )

            val chunkedAttributes = attributesList.chunked(2)

            chunkedAttributes.forEachIndexed { rowIndex, rowItems ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    rowItems.forEachIndexed { colIndex, attribute ->
                        val absoluteIndex = rowIndex * 2 + colIndex
                        val isLastItem = absoluteIndex == attributesList.lastIndex

                        OutlinedTextField(
                            value = attribute.value,
                            onValueChange = { newValue ->
                                viewModel.changeValue(attribute.id, newValue)
                            },
                            label = { Text(attribute.displayName) },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = if (isLastItem) ImeAction.Done else ImeAction.Next
                            ),
                            keyboardActions = KeyboardActions(onNext = {
                                focusManager.moveFocus(FocusDirection.Next)
                            }, onDone = { focusManager.clearFocus() }),
                            singleLine = true,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    if (rowItems.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }

            Button(
                onClick = { showSaveDialog = true },
                content = { Text("Save") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
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