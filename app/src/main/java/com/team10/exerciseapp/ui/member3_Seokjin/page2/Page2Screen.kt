package com.team10.exerciseapp.ui.member3_Seokjin.page2

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * 팀원 3 - Page 2 화면
 * Figma 디자인: 운동 선택 화면 (난이도별 필터)
 */
@Composable
fun Page2Screen() {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "Page 2",
                style = MaterialTheme.typography.headlineMedium
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // TODO: Figma 디자인 구현
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Page2ScreenPreview() {
    Page2Screen()
}

