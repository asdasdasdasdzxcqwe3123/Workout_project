package com.team10.exerciseapp.data.model

import java.time.LocalDate

/**
 * 일일 운동 통계
 * 단일 책임 원칙(SRP): 통계 정보만 담당
 */
data class DailyStats(
    val date: LocalDate,
    val totalCalories: Int,
    val totalWorkoutTime: Int, // 분 단위
    val workoutCount: Int,
    val completedSessions: List<WorkoutSession> = emptyList()
) {
    /**
     * 운동 완료 여부
     */
    fun hasWorkout(): Boolean = workoutCount > 0
    
    /**
     * 칼로리 목표 달성 여부 (예: 500칼로리)
     */
    fun isCalorieGoalMet(goal: Int = 500): Boolean = totalCalories >= goal
}

/**
 * 월간 통계
 */
data class MonthlyStats(
    val year: Int,
    val month: Int,
    val totalWorkouts: Int,
    val totalCalories: Int,
    val totalTime: Int,
    val dailyStats: Map<Int, DailyStats> = emptyMap() // 일자별 통계
) {
    /**
     * 특정 날짜의 통계 가져오기
     */
    fun getStatsForDay(day: Int): DailyStats? = dailyStats[day]
    
    /**
     * 운동한 날짜 목록
     */
    fun getWorkoutDays(): List<Int> = dailyStats.filter { it.value.hasWorkout() }.keys.toList()
}
