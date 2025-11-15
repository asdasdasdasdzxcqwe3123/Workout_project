package com.team10.exerciseapp.ui.member3_Seokjin.page6

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team10.exerciseapp.data.model.RoutineDay
import com.team10.exerciseapp.data.model.WorkoutRoutine
import com.team10.exerciseapp.data.repository.RoutineRepository
import com.team10.exerciseapp.data.repository.impl.RoutineRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Page6 (루틴 상세) ViewModel
 * 단일 책임 원칙(SRP): 루틴 상세 정보 관리만 담당
 */
class Page6ViewModel(
    private val routineRepository: RoutineRepository = RoutineRepositoryImpl()
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(Page6UiState())
    val uiState: StateFlow<Page6UiState> = _uiState.asStateFlow()
    
    /**
     * 루틴 상세 정보 로드
     */
    fun loadRoutineDetail(routineId: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            
            try {
                val routine = routineRepository.getRoutineById(routineId)
                val routineDays = routineRepository.getRoutineDays(routineId)
                
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    routine = routine,
                    routineDays = routineDays
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
    
    init {
        // 기본으로 5x5 스트렝스 루틴 로드 (ID = 1)
        loadRoutineDetail(1)
    }
}

/**
 * Page6 UI 상태
 */
data class Page6UiState(
    val isLoading: Boolean = false,
    val routine: WorkoutRoutine? = null,
    val routineDays: List<RoutineDay> = emptyList(),
    val error: String? = null
)
