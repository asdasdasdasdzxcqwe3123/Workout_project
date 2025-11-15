package com.team10.exerciseapp.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * 공통으로 사용되는 UI 컴포넌트들
 * 모든 팀원이 공유하여 사용
 * 단일 책임 원칙(SRP): 재사용 가능한 UI 컴포넌트만 담당
 */

// 공통 색상 정의
val PrimaryBlue = Color(0xFF5B7FFF)
val LightBlue = Color(0xFFE3F2FD)
val SuccessGreen = Color(0xFF4CAF50)
val TextGray = Color(0xFF757575)
val LightGray = Color(0xFFF5F5F5)

/**
 * 공통 버튼 컴포넌트
 */
@Composable
fun CommonButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryBlue
        )
    ) {
        Text(text = text)
    }
}

/**
 * 하단 네비게이션 바
 * 모든 페이지에서 공통으로 사용
 */
@Composable
fun BottomNavigationBar(
    currentRoute: String,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
        containerColor = Color.White
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, "홈") },
            label = { Text("홈", fontSize = 10.sp) },
            selected = currentRoute == "page1",
            onClick = { onNavigate("page1") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.AutoMirrored.Filled.List, "루틴") },
            label = { Text("루틴", fontSize = 10.sp) },
            selected = currentRoute == "page3",
            onClick = { onNavigate("page3") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.FitnessCenter, "운동") },
            label = { Text("운동", fontSize = 10.sp) },
            selected = currentRoute == "page2",
            onClick = { onNavigate("page2") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.CalendarToday, "기록") },
            label = { Text("기록", fontSize = 10.sp) },
            selected = currentRoute == "page4",
            onClick = { onNavigate("page4") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.AutoMirrored.Filled.MenuBook, "백과사전") },
            label = { Text("백과사전", fontSize = 10.sp) },
            selected = currentRoute == "page5",
            onClick = { onNavigate("page5") }
        )
    }
}

/**
 * 공통 상단 바
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTopBar(
    title: String,
    onBackClick: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            if (onBackClick != null) {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, "뒤로가기")
                }
            }
        },
        actions = actions,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        )
    )
}

