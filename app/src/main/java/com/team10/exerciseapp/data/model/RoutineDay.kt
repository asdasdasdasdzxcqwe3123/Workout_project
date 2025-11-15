package com.team10.exerciseapp.data.model

/**
 * 루틴의 특정 날짜 운동 계획
 * 예: 5x5 스트렝스 프로그램의 Day 1, Day 2 등
 */
data class RoutineDay(
    val dayNumber: Int,
    val title: String,
    val exercises: List<Exercise>,
    val sets: Int,
    val reps: Int,
    val description: String = ""
) {
    /**
     * 운동 목록을 문자열로 변환
     */
    fun getExerciseNames(): String {
        return exercises.joinToString(", ") { it.name }
    }
}
