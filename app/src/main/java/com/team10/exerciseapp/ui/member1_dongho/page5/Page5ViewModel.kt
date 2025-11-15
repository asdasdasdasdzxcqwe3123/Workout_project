package com.team10.exerciseapp.ui.member1_dongho.page5

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team10.exerciseapp.data.model.Exercise
import com.team10.exerciseapp.data.model.ExerciseCategory
import com.team10.exerciseapp.data.repository.ExerciseRepository
import com.team10.exerciseapp.data.repository.impl.ExerciseRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Page5 (운동 백과사전) ViewModel
 * 단일 책임 원칙(SRP): 운동 검색 및 필터링 로직만 담당
 */
class Page5ViewModel(
    private val exerciseRepository: ExerciseRepository = ExerciseRepositoryImpl()
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(Page5UiState())
    val uiState: StateFlow<Page5UiState> = _uiState.asStateFlow()
    
    init {
        loadExercises()
    }
    
    /**
     * 운동 목록 로드
     */
    private fun loadExercises() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            
            try {
                val exercises = exerciseRepository.getAllExercises()
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    allExercises = exercises,
                    filteredExercises = exercises
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
    
    /**
     * 카테고리 필터 변경
     */
    fun onCategorySelected(category: ExerciseCategory?) {
        _uiState.value = _uiState.value.copy(selectedCategory = category)
        applyFilters()
    }
    
    /**
     * 검색어 변경
     */
    fun onSearchQueryChanged(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
        applyFilters()
    }
    
    /**
     * 필터 적용
     */
    private fun applyFilters() {
        val state = _uiState.value
        var filtered = state.allExercises
        
        // 카테고리 필터
        state.selectedCategory?.let { category ->
            filtered = filtered.filter { it.category == category }
        }
        
        // 검색어 필터
        if (state.searchQuery.isNotBlank()) {
            filtered = filtered.filter { 
                it.name.contains(state.searchQuery, ignoreCase = true) ||
                it.targetMuscle.contains(state.searchQuery, ignoreCase = true)
            }
        }
        
        _uiState.value = state.copy(filteredExercises = filtered)
    }
}

/**
 * Page5 UI 상태
 */
data class Page5UiState(
    val isLoading: Boolean = false,
    val allExercises: List<Exercise> = emptyList(),
    val filteredExercises: List<Exercise> = emptyList(),
    val selectedCategory: ExerciseCategory? = null,
    val searchQuery: String = "",
    val error: String? = null
)
