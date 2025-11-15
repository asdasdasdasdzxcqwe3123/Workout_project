package com.team10.exerciseapp.ui.member2_junbeom.page4

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team10.exerciseapp.data.model.MonthlyStats
import com.team10.exerciseapp.data.repository.WorkoutRepository
import com.team10.exerciseapp.data.repository.impl.WorkoutRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

/**
 * Page4 (운동 기록 캘린더) ViewModel
 * 단일 책임 원칙(SRP): 운동 기록 통계 관리만 담당
 */
class Page4ViewModel(
    private val workoutRepository: WorkoutRepository = WorkoutRepositoryImpl()
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(Page4UiState())
    val uiState: StateFlow<Page4UiState> = _uiState.asStateFlow()
    
    init {
        val now = LocalDate.now()
        loadMonthlyStats(now.year, now.monthValue)
    }
    
    /**
     * 월간 통계 로드
     */
    fun loadMonthlyStats(year: Int, month: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            
            try {
                val stats = workoutRepository.getMonthlyStats(year, month)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    year = year,
                    month = month,
                    monthlyStats = stats
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
     * 이전 달로 이동
     */
    fun previousMonth() {
        val state = _uiState.value
        val newDate = LocalDate.of(state.year, state.month, 1).minusMonths(1)
        loadMonthlyStats(newDate.year, newDate.monthValue)
    }
    
    /**
     * 다음 달로 이동
     */
    fun nextMonth() {
        val state = _uiState.value
        val newDate = LocalDate.of(state.year, state.month, 1).plusMonths(1)
        loadMonthlyStats(newDate.year, newDate.monthValue)
    }
}

/**
 * Page4 UI 상태
 */
data class Page4UiState(
    val isLoading: Boolean = false,
    val year: Int = LocalDate.now().year,
    val month: Int = LocalDate.now().monthValue,
    val monthlyStats: MonthlyStats? = null,
    val error: String? = null
)
