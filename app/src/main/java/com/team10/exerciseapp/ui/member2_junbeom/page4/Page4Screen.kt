package com.team10.exerciseapp.ui.member2_junbeom.page4

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.team10.exerciseapp.ui.common.BottomNavigationBar
import com.team10.exerciseapp.ui.common.CommonTopBar
import com.team10.exerciseapp.ui.common.PrimaryBlue
import com.team10.exerciseapp.ui.common.LightGray
import com.team10.exerciseapp.ui.common.TextGray
import java.time.LocalDate
import java.time.YearMonth

/**
 * 팀원 2 - Page 4 화면 (운동 기록 캘린더)
 * Figma 디자인: 월별 통계 및 운동 기록
 * MVVM 패턴 적용
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Page4Screen(
    viewModel: Page4ViewModel = viewModel(),
    onNavigate: (String) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Scaffold(
        topBar = {
            CommonTopBar(
                title = "운동 기록",
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.MoreVert, "더보기")
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigationBar(
                currentRoute = "page4",
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
            
            // 이번 달 통계
            item {
                MonthlyStatsCard(
                    totalWorkouts = uiState.monthlyStats?.totalWorkouts ?: 12,
                    totalCalories = uiState.monthlyStats?.totalCalories ?: 540,
                    totalTime = uiState.monthlyStats?.totalTime ?: 2847
                )
            }
            
            // 캘린더
            item {
                CalendarCard(
                    year = uiState.year,
                    month = uiState.month,
                    workoutDays = uiState.monthlyStats?.getWorkoutDays() ?: listOf(2, 5, 7, 14, 21, 28, 30),
                    onPreviousMonth = { viewModel.previousMonth() },
                    onNextMonth = { viewModel.nextMonth() }
                )
            }
            
            // 최근 운동 기록
            item {
                Text(
                    "10월 30일 운동 기록",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            
            item {
                WorkoutRecordCard(
                    workoutName = "하체 운동",
                    emoji = "🏋️"
                )
            }
            
            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

/**
 * 월간 통계 카드
 */
@Composable
fun MonthlyStatsCard(
    totalWorkouts: Int,
    totalCalories: Int,
    totalTime: Int
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                "이번 달 통계",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatItem(
                    value = totalWorkouts.toString(),
                    label = "운동 횟수",
                    color = PrimaryBlue
                )
                StatItem(
                    value = totalCalories.toString(),
                    label = "칼로리(분)",
                    color = Color(0xFFFF9800)
                )
                StatItem(
                    value = totalTime.toString(),
                    label = "운동시간",
                    color = Color(0xFF4CAF50)
                )
            }
        }
    }
}

/**
 * 통계 아이템
 */
@Composable
fun StatItem(
    value: String,
    label: String,
    color: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            value,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = color
        )
        Text(
            label,
            fontSize = 12.sp,
            color = TextGray
        )
    }
}

/**
 * 캘린더 카드
 */
@Composable
fun CalendarCard(
    year: Int,
    month: Int,
    workoutDays: List<Int>,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            // 월 선택
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onPreviousMonth) {
                    Icon(Icons.Default.KeyboardArrowLeft, "이전 달")
                }
                Text(
                    "${year}년 ${month}월",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                IconButton(onClick = onNextMonth) {
                    Icon(Icons.Default.KeyboardArrowRight, "다음 달")
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // 요일 헤더
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                listOf("일", "월", "화", "수", "목", "금", "토").forEach { day ->
                    Text(
                        day,
                        fontSize = 12.sp,
                        color = TextGray,
                        modifier = Modifier.width(40.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // 날짜 그리드
            CalendarGrid(
                year = year,
                month = month,
                workoutDays = workoutDays
            )
        }
    }
}

/**
 * 캘린더 그리드
 */
@Composable
fun CalendarGrid(
    year: Int,
    month: Int,
    workoutDays: List<Int>
) {
    val yearMonth = YearMonth.of(year, month)
    val daysInMonth = yearMonth.lengthOfMonth()
    val firstDayOfWeek = yearMonth.atDay(1).dayOfWeek.value % 7
    
    Column {
        var dayCounter = 1
        for (week in 0..5) {
            if (dayCounter > daysInMonth) break
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (dayOfWeek in 0..6) {
                    if (week == 0 && dayOfWeek < firstDayOfWeek) {
                        Spacer(modifier = Modifier.width(40.dp))
                    } else if (dayCounter <= daysInMonth) {
                        CalendarDay(
                            day = dayCounter,
                            hasWorkout = workoutDays.contains(dayCounter),
                            isToday = dayCounter == LocalDate.now().dayOfMonth && 
                                     month == LocalDate.now().monthValue &&
                                     year == LocalDate.now().year
                        )
                        dayCounter++
                    } else {
                        Spacer(modifier = Modifier.width(40.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

/**
 * 캘린더 날짜
 */
@Composable
fun CalendarDay(
    day: Int,
    hasWorkout: Boolean,
    isToday: Boolean
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(
                color = when {
                    isToday -> PrimaryBlue
                    hasWorkout -> Color(0xFFE8F5E9)
                    else -> Color.Transparent
                },
                shape = CircleShape
            )
            .clickable { },
        contentAlignment = Alignment.Center
    ) {
        Text(
            day.toString(),
            fontSize = 14.sp,
            fontWeight = if (hasWorkout || isToday) FontWeight.Bold else FontWeight.Normal,
            color = when {
                isToday -> Color.White
                hasWorkout -> Color(0xFF4CAF50)
                else -> Color.Black
            }
        )
    }
}

/**
 * 운동 기록 카드
 */
@Composable
fun WorkoutRecordCard(
    workoutName: String,
    emoji: String
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
            Text(
                workoutName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Page4ScreenPreview() {
    Page4Screen()
}
