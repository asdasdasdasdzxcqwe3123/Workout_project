package com.team10.exerciseapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.team10.exerciseapp.ui.navigation.AppNavigation
import com.team10.exerciseapp.ui.theme.WorkoutProjectTheme

/**
 * 메인 액티비티
 * 앱의 진입점
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WorkoutProjectTheme {
                AppNavigation()
            }
        }
    }
}