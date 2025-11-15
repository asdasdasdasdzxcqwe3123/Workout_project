package com.team10.exerciseapp.data.repository.impl

import com.team10.exerciseapp.data.model.Exercise
import com.team10.exerciseapp.data.model.ExerciseCategory
import com.team10.exerciseapp.data.model.Difficulty
import com.team10.exerciseapp.data.repository.ExerciseRepository
import kotlinx.coroutines.delay

/**
 * 운동 데이터 저장소 구현체
 * 단일 책임 원칙(SRP): 운동 데이터 관리만 담당
 * 현재는 더미 데이터 사용, 추후 Room DB나 API로 교체 가능
 */
class ExerciseRepositoryImpl : ExerciseRepository {
    
    // 더미 데이터
    private val exercises = listOf(
        Exercise(1, "벤치프레스", ExerciseCategory.CHEST, Difficulty.BEGINNER, "가슴", "가슴 근육을 발달시키는 기본 운동", "", "💪"),
        Exercise(2, "스쿼트", ExerciseCategory.LEGS, Difficulty.BEGINNER, "하체", "하체 전체를 단련하는 운동", "", "🏋️"),
        Exercise(3, "데드리프트", ExerciseCategory.BACK, Difficulty.ADVANCED, "등", "등과 하체를 동시에 단련", "", "💪"),
        Exercise(4, "레그 프레스", ExerciseCategory.LEGS, Difficulty.BEGINNER, "하체", "허벅지 근육 강화", "", "🦵"),
        Exercise(5, "런지", ExerciseCategory.LEGS, Difficulty.INTERMEDIATE, "하체", "균형감각과 하체 근력 향상", "", "🚶"),
        Exercise(6, "벤치", ExerciseCategory.LEGS, Difficulty.INTERMEDIATE, "하체", "하체 근력 강화", "", "🏋️"),
        Exercise(7, "숄더 프레스", ExerciseCategory.SHOULDERS, Difficulty.INTERMEDIATE, "어깨", "어깨 근육 발달", "", "💪"),
        Exercise(8, "바이셉 컬", ExerciseCategory.ARMS, Difficulty.BEGINNER, "팔", "이두근 강화", "", "💪")
    )
    
    override suspend fun getAllExercises(): List<Exercise> {
        delay(100) // 네트워크 지연 시뮬레이션
        return exercises
    }
    
    override suspend fun getExercisesByCategory(category: ExerciseCategory): List<Exercise> {
        delay(100)
        return exercises.filter { it.category == category }
    }
    
    override suspend fun getExercisesByDifficulty(difficulty: Difficulty): List<Exercise> {
        delay(100)
        return exercises.filter { it.difficulty == difficulty }
    }
    
    override suspend fun searchExercises(query: String): List<Exercise> {
        delay(100)
        return exercises.filter { 
            it.name.contains(query, ignoreCase = true) ||
            it.targetMuscle.contains(query, ignoreCase = true)
        }
    }
    
    override suspend fun getExerciseById(id: Int): Exercise? {
        delay(100)
        return exercises.find { it.id == id }
    }
}
