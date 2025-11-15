package com.team10.exerciseapp.ui.member1_dongho.page5

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
import com.team10.exerciseapp.data.model.ExerciseCategory
import com.team10.exerciseapp.ui.common.BottomNavigationBar
import com.team10.exerciseapp.ui.common.CommonTopBar
import com.team10.exerciseapp.ui.common.PrimaryBlue
import com.team10.exerciseapp.ui.common.LightGray
import com.team10.exerciseapp.ui.common.TextGray

/**
 * 팀원 1 - Page 5 화면 (운동 백과사전)
 * Figma 디자인: 부위별 운동 검색 및 필터
 * MVVM 패턴 적용
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Page5Screen(
    viewModel: Page5ViewModel = viewModel(),
    onNavigate: (String) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Scaffold(
        topBar = {
            CommonTopBar(
                title = "운동 백과사전",
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Search, "검색")
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigationBar(
                currentRoute = "page5",
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
            // 검색창
            SearchBar(
                query = uiState.searchQuery,
                onQueryChange = { viewModel.onSearchQueryChanged(it) }
            )
            
            // 카테고리 필터
            CategoryFilter(
                selectedCategory = uiState.selectedCategory,
                onCategorySelected = { viewModel.onCategorySelected(it) }
            )
            
            // 운동 그리드
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(uiState.filteredExercises) { exercise ->
                    ExerciseCard(
                        name = exercise.name,
                        category = exercise.category.displayName,
                        difficulty = exercise.difficulty.displayName,
                        emoji = exercise.emoji.ifEmpty { "🏋️" },
                        onClick = { }
                    )
                }
            }
        }
    }
}

/**
 * 검색창
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        placeholder = { Text("운동을 검색해보세요") },
        leadingIcon = {
            Icon(Icons.Default.Search, "검색")
        },
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color.White,
            focusedBorderColor = PrimaryBlue,
            unfocusedBorderColor = Color.Transparent
        ),
        singleLine = true
    )
}

/**
 * 카테고리 필터
 */
@Composable
fun CategoryFilter(
    selectedCategory: ExerciseCategory?,
    onCategorySelected: (ExerciseCategory?) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // 전체 버튼
        FilterChip(
            selected = selectedCategory == null,
            onClick = { onCategorySelected(null) },
            label = { Text("전체") },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = PrimaryBlue,
                selectedLabelColor = Color.White
            )
        )
        
        // 카테고리 버튼들
        listOf(
            ExerciseCategory.CHEST,
            ExerciseCategory.BACK,
            ExerciseCategory.LEGS,
            ExerciseCategory.ARMS
        ).forEach { category ->
            FilterChip(
                selected = selectedCategory == category,
                onClick = { onCategorySelected(category) },
                label = { Text(category.displayName) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = PrimaryBlue,
                    selectedLabelColor = Color.White
                )
            )
        }
    }
}

/**
 * 운동 카드
 */
@Composable
fun ExerciseCard(
    name: String,
    category: String,
    difficulty: String,
    emoji: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                emoji,
                fontSize = 48.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                category,
                fontSize = 11.sp,
                color = TextGray
            )
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                color = when(difficulty) {
                    "초급" -> Color(0xFF4CAF50)
                    "중급" -> Color(0xFFFF9800)
                    else -> Color(0xFFF44336)
                },
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    difficulty,
                    fontSize = 10.sp,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Page5ScreenPreview() {
    Page5Screen()
}

