package com.team10.exerciseapp.ui.member2_junbeom.page4

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import java.time.LocalDate
import java.time.YearMonth

/**
 * 팀원 2 - Page 4 화면
 * 운동 기록 달력 화면
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Page4Screen() {
    var selectedMonth by remember { mutableStateOf(YearMonth.now()) }
    
    Scaffold(
        topBar = {
            RecordHeader()
        },
        bottomBar = {
            BottomNavigationBar()
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { Spacer(modifier = Modifier.height(8.dp)) }
            
            // 통계 카드
            item {
                StatisticsCard(
                    days = 13,
                    minutes = 33,
                    calories = 3847
                )
            }
            
            // 달력
            item {
                CalendarCard(
                    yearMonth = selectedMonth,
                    onPreviousMonth = { selectedMonth = selectedMonth.minusMonths(1) },
                    onNextMonth = { selectedMonth = selectedMonth.plusMonths(1) }
                )
            }
            
            // 이번 주 운동 기록
            item {
                WeeklyRecordCard()
            }
            
            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

/**
 * 기록 헤더
 */
@Composable
fun RecordHeader() {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "기록",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            
            IconButton(onClick = { /* 설정 */ }) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "설정"
                )
            }
        }
    }
}

/**
 * 통계 카드 (운동일, 시간, 칼로리)
 */
@Composable
fun StatisticsCard(
    days: Int,
    minutes: Int,
    calories: Int
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            StatItem(
                value = "$days",
                unit = "일",
                label = "운동일",
                color = Color(0xFF2196F3)
            )
            
            Divider(
                modifier = Modifier
                    .height(60.dp)
                    .width(1.dp),
                color = Color.LightGray
            )
            
            StatItem(
                value = "$minutes",
                unit = "분",
                label = "시간",
                color = Color(0xFFFF9800)
            )
            
            Divider(
                modifier = Modifier
                    .height(60.dp)
                    .width(1.dp),
                color = Color.LightGray
            )
            
            StatItem(
                value = "${calories}",
                unit = "kcal",
                label = "칼로리",
                color = Color(0xFF4CAF50)
            )
        }
    }
}

/**
 * 통계 항목
 */
@Composable
fun StatItem(
    value: String,
    unit: String,
    label: String,
    color: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = value,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = color
            )
            Text(
                text = unit,
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 2.dp, start = 2.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}

/**
 * 달력 카드
 */
@Composable
fun CalendarCard(
    yearMonth: YearMonth,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // 월 선택 헤더
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onPreviousMonth) {
                    Icon(Icons.Default.ChevronLeft, "이전 달")
                }
                
                Text(
                    text = "${yearMonth.year}년 ${yearMonth.monthValue}월",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                
                IconButton(onClick = onNextMonth) {
                    Icon(Icons.Default.ChevronRight, "다음 달")
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // 요일 헤더
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                listOf("일", "월", "화", "수", "목", "금", "토").forEach { day ->
                    Text(
                        text = day,
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // 달력 그리드
            CalendarGrid(yearMonth = yearMonth)
        }
    }
}

/**
 * 달력 그리드
 */
@Composable
fun CalendarGrid(yearMonth: YearMonth) {
    val firstDayOfMonth = yearMonth.atDay(1)
    val firstDayOfWeek = firstDayOfMonth.dayOfWeek.value % 7 // 일요일 = 0
    val daysInMonth = yearMonth.lengthOfMonth()
    
    // 운동한 날 (예시)
    val workoutDays = setOf(5, 8, 12, 15, 18, 22, 25, 28)
    
    Column {
        var dayCounter = 1
        
        // 주별로 행 생성 (최대 6주)
        for (week in 0..5) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (dayOfWeek in 0..6) {
                    val cellIndex = week * 7 + dayOfWeek
                    
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .padding(4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        if (cellIndex >= firstDayOfWeek && dayCounter <= daysInMonth) {
                            val isWorkoutDay = workoutDays.contains(dayCounter)
                            val isToday = dayCounter == LocalDate.now().dayOfMonth
                            
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .background(
                                        color = when {
                                            isToday -> MaterialTheme.colorScheme.primary
                                            isWorkoutDay -> Color(0xFF4CAF50)
                                            else -> Color.Transparent
                                        },
                                        shape = CircleShape
                                    )
                                    .clickable { /* 날짜 선택 */ },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "$dayCounter",
                                    fontSize = 12.sp,
                                    color = if (isToday || isWorkoutDay) Color.White else Color.Black
                                )
                            }
                            dayCounter++
                        }
                    }
                }
            }
            
            if (dayCounter > daysInMonth) break
        }
    }
}

/**
 * 이번 주 운동 기록 카드
 */
@Composable
fun WeeklyRecordCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "이번 주 운동 기록",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // 운동 기록 리스트 (예시)
            WorkoutRecordItem(
                date = "12월 5일",
                workoutName = "상체 운동",
                duration = "45분"
            )
        }
    }
}

/**
 * 운동 기록 아이템
 */
@Composable
fun WorkoutRecordItem(
    date: String,
    workoutName: String,
    duration: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = Color(0xFFE3F2FD),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.FitnessCenter,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            
            Column {
                Text(
                    text = workoutName,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = date,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
        
        Text(
            text = duration,
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}

/**
 * 하단 네비게이션 바
 */
@Composable
fun BottomNavigationBar() {
    NavigationBar(
        containerColor = Color.White
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, "홈") },
            label = { Text("홈", fontSize = 10.sp) },
            selected = false,
            onClick = { /* 홈 */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.List, "루틴") },
            label = { Text("루틴", fontSize = 10.sp) },
            selected = false,
            onClick = { /* 루틴 */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.FitnessCenter, "운동") },
            label = { Text("운동", fontSize = 10.sp) },
            selected = false,
            onClick = { /* 운동 */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Star, "기록") },
            label = { Text("기록", fontSize = 10.sp) },
            selected = true,
            onClick = { /* 기록 */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.MenuBook, "백과사전") },
            label = { Text("백과사전", fontSize = 10.sp) },
            selected = false,
            onClick = { /* 백과사전 */ }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Page4ScreenPreview() {
    Page4Screen()
}
