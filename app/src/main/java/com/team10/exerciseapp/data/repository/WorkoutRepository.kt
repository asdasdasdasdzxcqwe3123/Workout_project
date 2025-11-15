package com.team10.exerciseapp.data.repository

import com.team10.exerciseapp.data.model.WorkoutSession
import com.team10.exerciseapp.data.model.DailyStats
import com.team10.exerciseapp.data.model.MonthlyStats
import java.time.LocalDate

/**
 * 운동 기록 저장소 인터페이스
 * 의존성 역전 원칙(DIP): 구현체가 아닌 인터페이스에 의존
 */
interface WorkoutRepository {
    /**
     * 운동 세션 저장
     */
    suspend fun saveWorkoutSession(session: WorkoutSession): Boolean
    
    /**
     * 오늘의 운동 가져오기
     */
    suspend fun getTodayWorkouts(): List<WorkoutSession>
    
    /**
     * 최근 운동 가져오기
     */
    suspend fun getRecentWorkouts(limit: Int = 10): List<WorkoutSession>
    
    /**
     * 특정 날짜의 운동 가져오기
     */
    suspend fun getWorkoutsByDate(date: LocalDate): List<WorkoutSession>
    
    /**
     * 일일 통계 가져오기
     */
    suspend fun getDailyStats(date: LocalDate): DailyStats
    
    /**
     * 월간 통계 가져오기
     */
    suspend fun getMonthlyStats(year: Int, month: Int): MonthlyStats
}
