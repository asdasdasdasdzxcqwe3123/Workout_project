package com.team10.exerciseapp.ui.member1.page5

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * 팀원 1 - Page 5 화면
 */
@Composable
fun Page5Screen() {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "Page 5",
                style = MaterialTheme.typography.headlineMedium
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // TODO: Figma 디자인 구현
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Page5ScreenPreview() {
    Page5Screen()
}

