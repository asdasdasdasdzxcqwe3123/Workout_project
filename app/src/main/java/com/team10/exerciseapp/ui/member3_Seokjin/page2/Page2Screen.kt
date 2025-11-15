package com.team10.exerciseapp.ui.member3_Seokjin.page2

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
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
import com.team10.exerciseapp.data.model.Difficulty
import com.team10.exerciseapp.ui.common.BottomNavigationBar
import com.team10.exerciseapp.ui.common.CommonTopBar
import com.team10.exerciseapp.ui.common.PrimaryBlue
import com.team10.exerciseapp.ui.common.LightGray
import com.team10.exerciseapp.ui.common.TextGray

/**
 * 팀원 3 - Page 2 화면 (운동 루틴 목록)
 * Figma 디자인: 난이도별 루틴 필터 및 목록
 * MVVM 패턴 적용
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Page2Screen(
    viewModel: Page2ViewModel = viewModel(),
    onNavigate: (String) -> Unit = {},
    onRoutineClick: (Int) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Scaffold(
        topBar = {
            CommonTopBar(
                title = "운동 루틴",
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Search, "검색")
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigationBar(
                currentRoute = "page2",
                onNavigate = onNavigate
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LightGray)
                .padding(paddingValues)
        ) {
            // 난이도 필터
            DifficultyFilter(
                selectedDifficulty = uiState.selectedDifficulty,
                onDifficultySelected = { viewModel.onDifficultySelected(it) }
            )
            
            // 루틴 목록
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item { Spacer(modifier = Modifier.height(8.dp)) }
                
                items(uiState.filteredRoutines) { routine ->
                    RoutineCard(
                        name = routine.name,
                        description = routine.description,
                        difficulty = routine.difficulty.displayName,
                        difficultyColor = when(routine.difficulty) {
                            Difficulty.BEGINNER -> Color(0xFF4CAF50)
                            Difficulty.INTERMEDIATE -> Color(0xFFFF9800)
                            Difficulty.ADVANCED -> Color(0xFFF44336)
                        },
                        participants = routine.participantCount,
                        rating = routine.rating,
                        onClick = { onRoutineClick(routine.id) }
                    )
                }
                
                item { Spacer(modifier = Modifier.height(16.dp)) }
            }
        }
    }
}

/**
 * 난이도 필터
 */
@Composable
fun DifficultyFilter(
    selectedDifficulty: Difficulty?,
    onDifficultySelected: (Difficulty?) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // 전체
        FilterChip(
            selected = selectedDifficulty == null,
            onClick = { onDifficultySelected(null) },
            label = { Text("전체") },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = PrimaryBlue,
                selectedLabelColor = Color.White
            )
        )
        
        // 초급
        FilterChip(
            selected = selectedDifficulty == Difficulty.BEGINNER,
            onClick = { onDifficultySelected(Difficulty.BEGINNER) },
            label = { Text("초급") },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = PrimaryBlue,
                selectedLabelColor = Color.White
            )
        )
        
        // 중급
        FilterChip(
            selected = selectedDifficulty == Difficulty.INTERMEDIATE,
            onClick = { onDifficultySelected(Difficulty.INTERMEDIATE) },
            label = { Text("중급") },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = PrimaryBlue,
                selectedLabelColor = Color.White
            )
        )
        
        // 고급
        FilterChip(
            selected = selectedDifficulty == Difficulty.ADVANCED,
            onClick = { onDifficultySelected(Difficulty.ADVANCED) },
            label = { Text("고급") },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = PrimaryBlue,
                selectedLabelColor = Color.White
            )
        )
    }
}

/**
 * 루틴 카드
 */
@Composable
fun RoutineCard(
    name: String,
    description: String,
    difficulty: String,
    difficultyColor: Color,
    participants: Int,
    rating: Float,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            // 제목과 난이도
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                Surface(
                    color = difficultyColor,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        difficulty,
                        fontSize = 11.sp,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // 설명
            Text(
                description,
                fontSize = 13.sp,
                color = TextGray,
                lineHeight = 18.sp
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // 참여자 수와 평점
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        "👥",
                        fontSize = 14.sp
                    )
                    Text(
                        "${participants}명 참여",
                        fontSize = 12.sp,
                        color = TextGray
                    )
                }
                
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        Icons.Default.Star,
                        "평점",
                        tint = Color(0xFFFFC107),
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        rating.toString(),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Page2ScreenPreview() {
    Page2Screen()
}
