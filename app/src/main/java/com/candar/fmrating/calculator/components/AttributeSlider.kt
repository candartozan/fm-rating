package com.candar.fmrating.calculator.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSliderState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttributeSlider(
    modifier: Modifier = Modifier,
    title: String,
    value: Float,
    onChange: (Float) -> Unit
) {
    val sliderState = rememberSliderState(value = value, steps = 19, valueRange = 0f..20f)

    sliderState.onValueChange = {
        onChange(it)
        sliderState.value = it
    }
    sliderState.shouldAutoSnap = true

    Column(modifier = modifier) {
        Text(title)
        Slider(state = sliderState)
        Text(value.roundToInt().toString())
    }
}

@Preview
@Composable
private fun AttributeSliderPreview() {
    AttributeSlider(title = "Pace", value = 0f) { }
}