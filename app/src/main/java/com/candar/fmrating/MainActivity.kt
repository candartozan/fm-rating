package com.candar.fmrating

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.candar.fmrating.presentation.calculator.CalculatorScreen
import com.candar.fmrating.presentation.theme.FMRatingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { FMRatingTheme { CalculatorScreen() } }
    }
}