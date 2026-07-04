package com.candar.fmrating.presentation.calculator.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.candar.fmrating.presentation.theme.Typography

@Composable
fun RatingText(modifier: Modifier = Modifier, value: String) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(100),
        color = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
            text = value,
            style = Typography.displayMedium,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
private fun RatingTextPreview() {
    RatingText(value = "13.8")
}