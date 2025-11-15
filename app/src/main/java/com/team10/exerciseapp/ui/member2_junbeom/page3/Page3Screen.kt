package com.team10.exerciseapp.ui.member2_junbeom.page3

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Page 3 - 실전 운동 기록 화면 (완전 개편)
 * 
 * 핵심 개선:
 * 1. 휴식 타이머 상단 고정 (항상 보임)
 * 2. 현재 운동만 크게 표시
 * 3. 완료된 운동은 작게 접힘
 * 4. 스크롤 최소화
 */

// Figma 색상
val PrimaryBlue = Color(0xFF2196F3)
val LightBlue = Color(0xFFE3F2FD)
val SuccessGreen = Color(0xFF4CAF50)
val TextGray = Color(0xFF757575)
val LightGray = Color(0xFFF5F5F5)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Page3Screen() {
    // 운동 목록
    val exercises = remember {
        mutableStateListOf(
            ExerciseData(1, "스쿼트", "🏋️", false, mutableStateListOf(
                SetInfo(1, 20, 12, false),
                SetInfo(2, 20, 12, false),
                SetInfo(3, 20, 12, false)
            )),
            ExerciseData(2, "레그 프레스", "🦵", false, mutableStateListOf(
                SetInfo(1, 40, 12, false),
                SetInfo(2, 40, 12, false),
                SetInfo(3, 40, 12, false)
            )),
            ExerciseData(3, "런지", "🚶", false, mutableStateListOf(
                SetInfo(1, 15, 12, false),
                SetInfo(2, 15, 12, false)
            ))
        )
    }
    
    // 휴식 타이머
    var restTime by remember { mutableStateOf(90) }
    var isRestRunning by remember { mutableStateOf(false) }
    
    // 현재 진행 중인 운동 인덱스
    val currentExerciseIndex = exercises.indexOfFirst { !it.isComplete }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "하체 운동",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        bottomBar = {
            BottomNavBar()
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LightGray)
                .padding(paddingValues)
        ) {
            // 🔥 휴식 타이머 - 항상 상단 고정!
            RestTimerFixed(
                time = restTime,
                isRunning = isRestRunning,
                onStartPause = { isRestRunning = !isRestRunning }
            )
            
            // 운동 목록
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item { Spacer(modifier = Modifier.height(8.dp)) }
                
                items(exercises) { exercise ->
                    val isCurrent = exercises.indexOf(exercise) == currentExerciseIndex
                    
                    // 완료된 운동은 작게, 현재 운동은 크게
                    if (exercise.isComplete) {
                        CompletedExerciseCard(
                            exercise = exercise,
                            onClick = { 
                                // 클릭하면 다시 펼치기
                                exercise.isComplete = false
                            }
                        )
                    } else {
                        CurrentExerciseCard(
                            exercise = exercise,
                            isFocused = isCurrent,
                            onComplete = {
                                // 모든 세트 완료 시
                                if (exercise.sets.all { it.isComplete }) {
                                    exercise.isComplete = true
                                }
                            }
                        )
                    }
                }
                
                // 완료 버튼
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { /* 운동 완료 */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PrimaryBlue
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(Icons.Default.CheckCircle, null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("운동 완료", fontSize = 17.sp, fontWeight = FontWeight.Bold)
                    }
                }
                
                item { Spacer(modifier = Modifier.height(80.dp)) }
            }
        }
    }
}

/**
 * 🔥 휴식 타이머 - 상단 고정
 */
@Composable
fun RestTimerFixed(
    time: Int,
    isRunning: Boolean,
    onStartPause: () -> Unit
) {
    Surface(
        color = if (isRunning) Color(0xFFE8F5E9) else Color.White,
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 타이머 표시
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Default.AccessTime,
                    null,
                    tint = if (isRunning) SuccessGreen else TextGray,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = String.format("%02d:%02d", time / 60, time % 60),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isRunning) SuccessGreen else Color.Black
                )
            }
            
            // 시작/정지 버튼
            Button(
                onClick = onStartPause,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isRunning) Color(0xFFFF9800) else SuccessGreen
                ),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.height(40.dp)
            ) {
                Icon(
                    if (isRunning) Icons.Default.Pause else Icons.Default.PlayArrow,
                    null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    if (isRunning) "정지" else "시작",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

/**
 * 현재 진행 중인 운동 카드 (큰 카드)
 */
@Composable
fun CurrentExerciseCard(
    exercise: ExerciseData,
    isFocused: Boolean,
    onComplete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isFocused) Color.White else Color(0xFFFAFAFA)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isFocused) 8.dp else 2.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            // 헤더
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = exercise.emoji,
                    fontSize = 40.sp
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = exercise.name,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                
                // 진행률
                val completedSets = exercise.sets.count { it.isComplete }
                val totalSets = exercise.sets.size
                Text(
                    text = "$completedSets/$totalSets",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryBlue
                )
            }
            
            Spacer(modifier = Modifier.height(20.dp))
            
            // 세트 목록
            exercise.sets.forEach { set ->
                SetRowCompact(
                    set = set,
                    onWeightChange = { delta ->
                        set.weight = (set.weight + delta).coerceAtLeast(0)
                    },
                    onRepsChange = { delta ->
                        set.reps = (set.reps + delta).coerceAtLeast(0)
                    },
                    onToggleComplete = {
                        set.isComplete = !set.isComplete
                        onComplete()
                    }
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

/**
 * 완료된 운동 카드 (작은 카드)
 */
@Composable
fun CompletedExerciseCard(
    exercise: ExerciseData,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = LightBlue
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.CheckCircle,
                null,
                tint = SuccessGreen,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = exercise.emoji,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = exercise.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "${exercise.sets.size}세트 완료",
                fontSize = 13.sp,
                color = TextGray
            )
        }
    }
}

/**
 * 컴팩트 세트 행
 */
@Composable
fun SetRowCompact(
    set: SetInfo,
    onWeightChange: (Int) -> Unit,
    onRepsChange: (Int) -> Unit,
    onToggleComplete: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = if (set.isComplete) LightBlue else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // 체크박스 + 세트 번호
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(80.dp)
        ) {
            Checkbox(
                checked = set.isComplete,
                onCheckedChange = { onToggleComplete() },
                colors = CheckboxDefaults.colors(checkedColor = PrimaryBlue)
            )
            Text(
                text = "${set.number}세트",
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = if (set.isComplete) TextGray else Color.Black
            )
        }
        
        // 무게 조절
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            IconButton(
                onClick = { onWeightChange(-5) },
                modifier = Modifier
                    .size(40.dp)
                    .background(LightGray, CircleShape)
            ) {
                Icon(Icons.Default.Remove, "감소", modifier = Modifier.size(20.dp))
            }
            
            Text(
                text = "${set.weight}kg",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.width(60.dp),
                textAlign = TextAlign.Center
            )
            
            IconButton(
                onClick = { onWeightChange(5) },
                modifier = Modifier
                    .size(40.dp)
                    .background(PrimaryBlue, CircleShape)
            ) {
                Icon(
                    Icons.Default.Add,
                    "증가",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
        
        Text("×", fontSize = 20.sp, color = TextGray)
        
        // 횟수 조절
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            IconButton(
                onClick = { onRepsChange(-1) },
                modifier = Modifier
                    .size(40.dp)
                    .background(LightGray, CircleShape)
            ) {
                Icon(Icons.Default.Remove, "감소", modifier = Modifier.size(20.dp))
            }
            
            Text(
                text = "${set.reps}회",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.width(50.dp),
                textAlign = TextAlign.Center
            )
            
            IconButton(
                onClick = { onRepsChange(1) },
                modifier = Modifier
                    .size(40.dp)
                    .background(PrimaryBlue, CircleShape)
            ) {
                Icon(
                    Icons.Default.Add,
                    "증가",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

/**
 * 하단 네비게이션
 */
@Composable
fun BottomNavBar() {
    NavigationBar(containerColor = Color.White) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, "홈") },
            label = { Text("홈", fontSize = 10.sp) },
            selected = false,
            onClick = { }
        )
        NavigationBarItem(
            icon = { Icon(Icons.AutoMirrored.Filled.List, "루틴") },
            label = { Text("루틴", fontSize = 10.sp) },
            selected = false,
            onClick = { }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.FitnessCenter, "운동") },
            label = { Text("운동", fontSize = 10.sp) },
            selected = true,
            onClick = { }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Star, "기록") },
            label = { Text("기록", fontSize = 10.sp) },
            selected = false,
            onClick = { }
        )
        NavigationBarItem(
            icon = { Icon(Icons.AutoMirrored.Filled.MenuBook, "백과사전") },
            label = { Text("백과사전", fontSize = 10.sp) },
            selected = false,
            onClick = { }
        )
    }
}

/**
 * 데이터 모델
 */
data class ExerciseData(
    val id: Int,
    val name: String,
    val emoji: String,
    var isComplete: Boolean,
    val sets: MutableList<SetInfo>
)

data class SetInfo(
    val number: Int,
    var weight: Int,
    var reps: Int,
    var isComplete: Boolean
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Page3ScreenPreview() {
    Page3Screen()
}
