package com.team10.exerciseapp.ui.member2_junbeom.page3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.team10.exerciseapp.ui.common.PrimaryBlue
import com.team10.exerciseapp.ui.common.LightGray
import com.team10.exerciseapp.ui.common.TextGray

/**
 * 팀원 2 - Page 3 화면 (하체 운동 기록)
 * Figma 디자인: 운동 세트 기록 화면
 * MVVM 패턴 적용
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Page3Screen(
    viewModel: Page3ViewModel = viewModel(),
    onNavigate: (String) -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            uiState.workoutName,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            uiState.date,
                            fontSize = 12.sp,
                            color = TextGray
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, "뒤로가기")
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.MoreVert, "더보기")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        bottomBar = {
            BottomNavigationBar(
                currentRoute = "page3",
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
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item { Spacer(modifier = Modifier.height(8.dp)) }
                
                // 운동 목록
                itemsIndexed(uiState.exercises) { exerciseIndex, exercise ->
                    ExerciseSection(
                        exerciseName = exercise.name,
                        sets = exercise.sets,
                        onWeightChange = { setIndex, delta ->
                            viewModel.updateSetWeight(exerciseIndex, setIndex, delta)
                        },
                        onRepsChange = { setIndex, delta ->
                            viewModel.updateSetReps(exerciseIndex, setIndex, delta)
                        },
                        onToggleComplete = { setIndex ->
                            viewModel.toggleSetComplete(exerciseIndex, setIndex)
                        }
                    )
                }
                
                item { Spacer(modifier = Modifier.height(16.dp)) }
            }
            
            // 하단 운동 완료 버튼
            Button(
                onClick = { viewModel.completeWorkout() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryBlue
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    "운동 완료",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

/**
 * 운동 섹션 (운동 이름 + 세트 목록)
 */
@Composable
fun ExerciseSection(
    exerciseName: String,
    sets: List<com.team10.exerciseapp.data.model.WorkoutSet>,
    onWeightChange: (Int, Int) -> Unit,
    onRepsChange: (Int, Int) -> Unit,
    onToggleComplete: (Int) -> Unit
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
            // 운동 이름
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    exerciseName,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                IconButton(
                    onClick = { },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        Icons.Default.CheckCircle,
                        "완료",
                        tint = TextGray
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // 세트 목록
            sets.forEachIndexed { index, set ->
                SetRow(
                    setNumber = set.setNumber,
                    weight = set.weight,
                    reps = set.reps,
                    isCompleted = set.isCompleted,
                    onWeightChange = { delta -> onWeightChange(index, delta) },
                    onRepsChange = { delta -> onRepsChange(index, delta) },
                    onToggleComplete = { onToggleComplete(index) }
                )
                if (index < sets.size - 1) {
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}

/**
 * 세트 행
 */
@Composable
fun SetRow(
    setNumber: Int,
    weight: Int,
    reps: Int,
    isCompleted: Boolean,
    onWeightChange: (Int) -> Unit,
    onRepsChange: (Int) -> Unit,
    onToggleComplete: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = if (isCompleted) LightGray else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // 세트 번호
        Text(
            "${setNumber}세트",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.width(60.dp)
        )
        
        // 무게 조절
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                "무게",
                fontSize = 12.sp,
                color = TextGray
            )
            IconButton(
                onClick = { onWeightChange(-5) },
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    Icons.Default.Remove,
                    "감소",
                    modifier = Modifier.size(16.dp)
                )
            }
            Text(
                "${weight}kg",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.width(50.dp),
                textAlign = TextAlign.Center
            )
            IconButton(
                onClick = { onWeightChange(5) },
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    Icons.Default.Add,
                    "증가",
                    modifier = Modifier.size(16.dp)
                )
            }
        }
        
        Text(
            "×",
            fontSize = 16.sp,
            color = TextGray
        )
        
        // 횟수 조절
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                "횟수",
                fontSize = 12.sp,
                color = TextGray
            )
            IconButton(
                onClick = { onRepsChange(-1) },
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    Icons.Default.Remove,
                    "감소",
                    modifier = Modifier.size(16.dp)
                )
            }
            Text(
                "${reps}회",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.width(40.dp),
                textAlign = TextAlign.Center
            )
            IconButton(
                onClick = { onRepsChange(1) },
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    Icons.Default.Add,
                    "증가",
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Page3ScreenPreview() {
    Page3Screen()
}
