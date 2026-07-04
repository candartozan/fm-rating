package com.candar.fmrating.presentation.calculator.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AttributeSlider(
    modifier: Modifier = Modifier, title: String, value: Float, onChange: (Float) -> Unit
) {
    val textStyle = MaterialTheme.typography.titleMedium
    val textColor = MaterialTheme.colorScheme.onSurface

    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceContainer,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = value.toInt().toString(), color = textColor, style = textStyle)
                Text(text = title, color = textColor, style = textStyle)
            }
            Slider(
                value = value, onValueChange = { onChange(it) }, valueRange = 0f..20f, steps = 19
            )
        }
    }
}

@Preview
@Composable
private fun AttributeSliderPreview() {
    AttributeSlider(title = "Pace", value = 0f) { }
}