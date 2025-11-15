package com.team10.exerciseapp.ui.member1.page1

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * 팀원 1 - Page 1 화면
 * Figma 디자인: 홈 화면 (오늘의 운동, 통계, 최근 운동)
 */
@Composable
fun Page1Screen() {
    Scaffold(
        topBar = {
            // 헤더 구현
        },
        bottomBar = {
            // 하단 네비게이션 바 구현
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "Page 1 - 홈 화면",
                style = MaterialTheme.typography.headlineMedium
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // TODO: Figma 디자인 구현
            // 1. 오늘의 운동 카드
            // 2. 통계 (칼로리, 운동시간)
            // 3. 최근 운동 리스트
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Page1ScreenPreview() {
    Page1Screen()
}

