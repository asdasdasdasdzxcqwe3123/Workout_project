package com.team10.exerciseapp.data.model

/**
 * 운동 루틴 (여러 운동의 조합)
 * 단일 책임 원칙(SRP): 루틴 정보만 담당
 */
data class WorkoutRoutine(
    val id: Int,
    val name: String,
    val description: String,
    val difficulty: Difficulty,
    val exercises: List<Exercise>,
    val targetSets: Int,
    val targetReps: Int,
    val estimatedDuration: Int, // 분 단위
    val participantCount: Int = 0,
    val rating: Float = 0f
) {
    /**
     * 루틴의 총 운동 개수
     */
    fun getExerciseCount(): Int = exercises.size
    
    /**
     * 루틴 설명 요약
     */
    fun getSummary(): String {
        val categories = exercises.map { it.category.displayName }.distinct()
        return "${categories.joinToString(", ")} 운동"
    }
}
