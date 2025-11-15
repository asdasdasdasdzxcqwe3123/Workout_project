package com.team10.exerciseapp.ui.member1_dongho.page1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team10.exerciseapp.data.model.WorkoutSession
import com.team10.exerciseapp.data.repository.WorkoutRepository
import com.team10.exerciseapp.data.repository.impl.WorkoutRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Page1 (홈 화면) ViewModel
 * 단일 책임 원칙(SRP): 홈 화면의 상태 관리와 비즈니스 로직만 담당
 * 의존성 역전 원칙(DIP): Repository 인터페이스에 의존
 */
class Page1ViewModel(
    private val workoutRepository: WorkoutRepository = WorkoutRepositoryImpl()
) : ViewModel() {
    
    // UI 상태
    private val _uiState = MutableStateFlow(Page1UiState())
    val uiState: StateFlow<Page1UiState> = _uiState.asStateFlow()
    
    init {
        loadData()
    }
    
    /**
     * 데이터 로드
     */
    private fun loadData() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            
            try {
                val todayWorkouts = workoutRepository.getTodayWorkouts()
                val recentWorkouts = workoutRepository.getRecentWorkouts(5)
                val todayStats = workoutRepository.getDailyStats(java.time.LocalDate.now())
                
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    todayWorkouts = todayWorkouts,
                    recentWorkouts = recentWorkouts,
                    totalCalories = todayStats.totalCalories,
                    totalWorkoutTime = todayStats.totalWorkoutTime
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
     * 데이터 새로고침
     */
    fun refresh() {
        loadData()
    }
}

/**
 * Page1 UI 상태
 * 불변 데이터 클래스로 상태 관리
 */
data class Page1UiState(
    val isLoading: Boolean = false,
    val todayWorkouts: List<WorkoutSession> = emptyList(),
    val recentWorkouts: List<WorkoutSession> = emptyList(),
    val totalCalories: Int = 247,
    val totalWorkoutTime: Int = 45,
    val error: String? = null
)
