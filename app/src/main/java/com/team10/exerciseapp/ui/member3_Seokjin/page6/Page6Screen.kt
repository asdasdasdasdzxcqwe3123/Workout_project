package com.team10.exerciseapp.ui.member3_Seokjin.page6

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.team10.exerciseapp.ui.common.BottomNavigationBar
import com.team10.exerciseapp.ui.common.CommonTopBar
import com.team10.exerciseapp.ui.common.PrimaryBlue
import com.team10.exerciseapp.ui.common.LightGray
import com.team10.exerciseapp.ui.common.TextGray

/**
 * 팀원 3 - Page 6 화면 (5x5 스트렝스 상세)
 * Figma 디자인: 루틴 상세 정보 화면
 * MVVM 패턴 적용
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Page6Screen(
    viewModel: Page6ViewModel = viewModel(),
    onNavigate: (String) -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        uiState.routine?.name ?: "5×5 스트렝스",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, "뒤로가기")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        bottomBar = {
            BottomNavigationBar(
                currentRoute = "page6",
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
            
            // 루틴 정보 카드
            item {
                RoutineInfoCard(
                    routineName = uiState.routine?.name ?: "5×5 스트렝스",
                    difficulty = uiState.routine?.difficulty?.displayName ?: "초급"
                )
            }
            
            // 주간 구성 타이틀
            item {
                Text(
                    "주간 구성",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            
            // 일별 운동 계획
            items(uiState.routineDays) { day ->
                RoutineDayCard(
                    dayNumber = day.dayNumber,
                    title = day.title,
                    exercises = day.getExerciseNames(),
                    sets = day.sets
                )
            }
            
            // 루틴 설명 섹션
            item {
                Text(
                    "루틴 설명",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            
            item {
                RoutineDescriptionCard(
                    description = uiState.routine?.description ?: 
                        "5×5 스트렝스는 기초 근력 향상을 위한 가장 구조화된 프로그램입니다. 5세트 마다 5회의 반복으로 진행하며, 각 운동 사이에 충분한 휴식을 취하여 근력을 극대화합니다."
                )
            }
            
            // 시작하기 버튼
            item {
                Button(
                    onClick = { onNavigate("page2") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryBlue
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        "이 루틴 시작하기",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            
            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

/**
 * 루틴 정보 카드
 */
@Composable
fun RoutineInfoCard(
    routineName: String,
    difficulty: String
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
                routineName,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Surface(
                color = Color(0xFF4CAF50),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    difficulty,
                    fontSize = 12.sp,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }
        }
    }
}

/**
 * 루틴 일별 카드
 */
@Composable
fun RoutineDayCard(
    dayNumber: Int,
    title: String,
    exercises: String,
    sets: Int
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
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    "Day $dayNumber: $title",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    exercises,
                    fontSize = 13.sp,
                    color = TextGray
                )
            }
            Text(
                "${sets}세트",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = PrimaryBlue
            )
        }
    }
}

/**
 * 루틴 설명 카드
 */
@Composable
fun RoutineDescriptionCard(
    description: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                description,
                fontSize = 14.sp,
                color = TextGray,
                lineHeight = 20.sp
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Page6ScreenPreview() {
    Page6Screen()
}

