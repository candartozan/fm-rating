package com.candar.fmrating.presentation.calculator.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun SaveDialog(onDismissRequest: () -> Unit, onSave: (String) -> Unit) {
    var name by rememberSaveable { mutableStateOf("") }

    AlertDialog(onDismissRequest = {
        onDismissRequest()
        name = ""
    }, title = { Text("Save") }, text = {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Enter Name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
    }, confirmButton = {
        TextButton(
            onClick = {
                onSave(name)
                onDismissRequest()
                name = ""
            }, enabled = name.isNotBlank()
        ) {
            Text("Save")
        }
    }, dismissButton = {
        TextButton(
            onClick = {
                onDismissRequest()
                name = ""
            }) {
            Text("Cancel")
        }
    })
}