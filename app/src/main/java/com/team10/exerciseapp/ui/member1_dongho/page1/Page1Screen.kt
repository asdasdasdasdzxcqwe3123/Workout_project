package com.team10.exerciseapp.ui.member1_dongho.page1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.team10.exerciseapp.ui.common.BottomNavigationBar
import com.team10.exerciseapp.ui.common.PrimaryBlue
import com.team10.exerciseapp.ui.common.LightGray
import com.team10.exerciseapp.ui.common.TextGray

/**
 * 팀원 1 - Page 1 화면 (홈)
 * Figma 디자인: 오늘의 운동, 칼로리/운동 통계, 최근 운동 리스트
 * MVVM 패턴 적용: UI는 ViewModel의 상태만 관찰
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Page1Screen(
    viewModel: Page1ViewModel = viewModel(),
    onNavigate: (String) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            "안녕하세요, 김민수님!",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "오늘도 파이팅하세요 💪",
                            fontSize = 12.sp,
                            color = TextGray
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Notifications, "알림")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        bottomBar = {
            BottomNavigationBar(
                currentRoute = "page1",
                onNavigate = onNavigate
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(LightGray)
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { Spacer(modifier = Modifier.height(8.dp)) }
            
            // 오늘의 운동 카드
            item {
                TodayWorkoutCard(
                    workoutName = "하체 운동",
                    progress = 3,
                    total = 5,
                    onStartClick = { onNavigate("page2") }
                )
            }
            
            // 통계 카드
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    StatsCard(
                        icon = "🔥",
                        value = uiState.totalCalories.toString(),
                        label = "칼로리 소모",
                        modifier = Modifier.weight(1f)
                    )
                    StatsCard(
                        icon = "⏱️",
                        value = uiState.totalWorkoutTime.toString(),
                        label = "운동 시간",
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            
            // 최근 운동 섹션
            item {
                Text(
                    "최근 운동",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            
            // 최근 운동 리스트
            items(uiState.recentWorkouts) { workout ->
                RecentWorkoutItem(
                    emoji = workout.exercise.emoji,
                    name = workout.exercise.name,
                    sets = workout.getCompletedSetsCount(),
                    totalSets = workout.getTotalSetsCount(),
                    date = workout.getFormattedDate()
                )
            }
            
            // 더미 데이터 (실제 데이터가 없을 때)
            if (uiState.recentWorkouts.isEmpty()) {
                item {
                    RecentWorkoutItem(
                        emoji = "🏋️",
                        name = "상체 운동",
                        sets = 5,
                        totalSets = 5,
                        date = "오늘"
                    )
                }
                item {
                    RecentWorkoutItem(
                        emoji = "🦵",
                        name = "유산소",
                        sets = 2,
                        totalSets = 3,
                        date = "어제"
                    )
                }
            }
            
            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

/**
 * 오늘의 운동 카드
 */
@Composable
fun TodayWorkoutCard(
    workoutName: String,
    progress: Int,
    total: Int,
    onStartClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = PrimaryBlue
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                "오늘의 운동",
                fontSize = 14.sp,
                color = Color.White.copy(alpha = 0.9f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                workoutName,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(12.dp))
            
            // 진행률 바
            Text(
                "$progress/$total 완료",
                fontSize = 12.sp,
                color = Color.White.copy(alpha = 0.9f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = progress.toFloat() / total,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp),
                color = Color.White,
                trackColor = Color.White.copy(alpha = 0.3f)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // 운동 시작하기 버튼
            Button(
                onClick = onStartClick,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    "운동 시작하기",
                    color = PrimaryBlue,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

/**
 * 통계 카드
 */
@Composable
fun StatsCard(
    icon: String,
    value: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                icon,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                value,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                label,
                fontSize = 12.sp,
                color = TextGray
            )
        }
    }
}

/**
 * 최근 운동 아이템
 */
@Composable
fun RecentWorkoutItem(
    emoji: String,
    name: String,
    sets: Int,
    totalSets: Int,
    date: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                emoji,
                fontSize = 32.sp
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "$sets/$totalSets 세트",
                    fontSize = 12.sp,
                    color = TextGray
                )
            }
            Text(
                date,
                fontSize = 12.sp,
                color = TextGray
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Page1ScreenPreview() {
    Page1Screen()
}

