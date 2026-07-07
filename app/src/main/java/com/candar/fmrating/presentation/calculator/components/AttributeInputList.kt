package com.candar.fmrating.presentation.calculator.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.candar.fmrating.presentation.calculator.PlayerAttribute
import kotlin.math.roundToInt

@Composable
fun AttributeInputList(
    modifier: Modifier = Modifier, items: List<PlayerAttribute>, onValueChange: (Int, Int) -> Unit
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items.chunked(2).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                rowItems.forEach { attribute ->
                    AttributeSlider(
                        modifier = modifier.weight(1f),
                        title = attribute.displayName,
                        value = attribute.value.toFloat(),
                        onChange = { onValueChange(attribute.id, it.roundToInt()) })
                }
                if (rowItems.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Preview
@Composable
private fun AttributeInputListPreview() {
    AttributeInputList(
        items = listOf(
            PlayerAttribute(0, "DisplayName", 0, 0),
            PlayerAttribute(0, "DisplayName", 0, 0),
            PlayerAttribute(0, "DisplayName", 0, 0)
        ), onValueChange = { _, _ -> })
}