package com.team10.exerciseapp.ui.member3_Seokjin.page2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team10.exerciseapp.data.model.Difficulty
import com.team10.exerciseapp.data.model.WorkoutRoutine
import com.team10.exerciseapp.data.repository.RoutineRepository
import com.team10.exerciseapp.data.repository.impl.RoutineRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Page2 (운동 루틴 목록) ViewModel
 * 단일 책임 원칙(SRP): 루틴 목록 관리만 담당
 */
class Page2ViewModel(
    private val routineRepository: RoutineRepository = RoutineRepositoryImpl()
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(Page2UiState())
    val uiState: StateFlow<Page2UiState> = _uiState.asStateFlow()
    
    init {
        loadRoutines()
    }
    
    /**
     * 루틴 목록 로드
     */
    private fun loadRoutines() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            
            try {
                val routines = routineRepository.getAllRoutines()
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    allRoutines = routines,
                    filteredRoutines = routines
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
     * 난이도 필터 변경
     */
    fun onDifficultySelected(difficulty: Difficulty?) {
        _uiState.value = _uiState.value.copy(selectedDifficulty = difficulty)
        applyFilter()
    }
    
    /**
     * 필터 적용
     */
    private fun applyFilter() {
        val state = _uiState.value
        val filtered = if (state.selectedDifficulty == null) {
            state.allRoutines
        } else {
            state.allRoutines.filter { it.difficulty == state.selectedDifficulty }
        }
        _uiState.value = state.copy(filteredRoutines = filtered)
    }
}

/**
 * Page2 UI 상태
 */
data class Page2UiState(
    val isLoading: Boolean = false,
    val allRoutines: List<WorkoutRoutine> = emptyList(),
    val filteredRoutines: List<WorkoutRoutine> = emptyList(),
    val selectedDifficulty: Difficulty? = null,
    val error: String? = null
)
