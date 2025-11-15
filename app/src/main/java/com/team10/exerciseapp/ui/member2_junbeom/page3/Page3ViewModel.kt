package com.team10.exerciseapp.ui.member2_junbeom.page3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team10.exerciseapp.data.model.WorkoutSet
import com.team10.exerciseapp.data.repository.WorkoutRepository
import com.team10.exerciseapp.data.repository.impl.WorkoutRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Page3 (하체 운동 기록) ViewModel
 * 단일 책임 원칙(SRP): 운동 기록 상태 관리만 담당
 */
class Page3ViewModel(
    private val workoutRepository: WorkoutRepository = WorkoutRepositoryImpl()
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(Page3UiState())
    val uiState: StateFlow<Page3UiState> = _uiState.asStateFlow()
    
    /**
     * 세트 무게 변경
     */
    fun updateSetWeight(exerciseIndex: Int, setIndex: Int, delta: Int) {
        val currentState = _uiState.value
        val updatedExercises = currentState.exercises.toMutableList()
        val exercise = updatedExercises[exerciseIndex]
        val updatedSets = exercise.sets.toMutableList()
        val set = updatedSets[setIndex]
        
        updatedSets[setIndex] = set.copy(weight = (set.weight + delta).coerceAtLeast(0))
        updatedExercises[exerciseIndex] = exercise.copy(sets = updatedSets)
        
        _uiState.value = currentState.copy(exercises = updatedExercises)
    }
    
    /**
     * 세트 횟수 변경
     */
    fun updateSetReps(exerciseIndex: Int, setIndex: Int, delta: Int) {
        val currentState = _uiState.value
        val updatedExercises = currentState.exercises.toMutableList()
        val exercise = updatedExercises[exerciseIndex]
        val updatedSets = exercise.sets.toMutableList()
        val set = updatedSets[setIndex]
        
        updatedSets[setIndex] = set.copy(reps = (set.reps + delta).coerceAtLeast(0))
        updatedExercises[exerciseIndex] = exercise.copy(sets = updatedSets)
        
        _uiState.value = currentState.copy(exercises = updatedExercises)
    }
    
    /**
     * 세트 완료 토글
     */
    fun toggleSetComplete(exerciseIndex: Int, setIndex: Int) {
        val currentState = _uiState.value
        val updatedExercises = currentState.exercises.toMutableList()
        val exercise = updatedExercises[exerciseIndex]
        val updatedSets = exercise.sets.toMutableList()
        val set = updatedSets[setIndex]
        
        updatedSets[setIndex] = set.copy(isCompleted = !set.isCompleted)
        updatedExercises[exerciseIndex] = exercise.copy(sets = updatedSets)
        
        _uiState.value = currentState.copy(exercises = updatedExercises)
    }
    
    /**
     * 운동 완료
     */
    fun completeWorkout() {
        viewModelScope.launch {
            // 실제로는 Repository를 통해 저장
            _uiState.value = _uiState.value.copy(isCompleted = true)
        }
    }
}

/**
 * Page3 UI 상태
 */
data class Page3UiState(
    val workoutName: String = "하체 운동",
    val date: String = "2024년 10월 30일",
    val exercises: List<ExerciseState> = listOf(
        ExerciseState(
            name = "레그 프레스",
            sets = listOf(
                WorkoutSet(1, 0, 0, false),
                WorkoutSet(2, 0, 0, false)
            )
        ),
        ExerciseState(
            name = "런지",
            sets = listOf(
                WorkoutSet(1, 0, 0, false)
            )
        )
    ),
    val isCompleted: Boolean = false
)

/**
 * 운동 상태
 */
data class ExerciseState(
    val name: String,
    val sets: List<WorkoutSet>
)
