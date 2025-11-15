package com.team10.exerciseapp.data.repository.impl

import com.team10.exerciseapp.data.model.*
import com.team10.exerciseapp.data.repository.WorkoutRepository
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * 운동 기록 저장소 구현체
 * 단일 책임 원칙(SRP): 운동 기록 관리만 담당
 */
class WorkoutRepositoryImpl : WorkoutRepository {
    
    // 더미 데이터 (실제로는 Room DB 사용)
    private val workoutSessions = mutableListOf(
        WorkoutSession(
            id = 1,
            exercise = Exercise(2, "스쿼트", ExerciseCategory.LEGS, Difficulty.BEGINNER, "하체", "", "", "🏋️"),
            sets = listOf(
                WorkoutSet(1, 20, 12, true),
                WorkoutSet(2, 20, 12, true),
                WorkoutSet(3, 20, 12, false)
            ),
            date = LocalDateTime.now().minusDays(1),
            isCompleted = false
        ),
        WorkoutSession(
            id = 2,
            exercise = Exercise(5, "유산소", ExerciseCategory.CARDIO, Difficulty.BEGINNER, "전신", "", "", "🏃"),
            sets = listOf(WorkoutSet(1, 0, 30, true)),
            date = LocalDateTime.now().minusDays(2),
            isCompleted = true
        )
    )
    
    override suspend fun saveWorkoutSession(session: WorkoutSession): Boolean {
        delay(100)
        workoutSessions.add(session)
        return true
    }
    
    override suspend fun getTodayWorkouts(): List<WorkoutSession> {
        delay(100)
        val today = LocalDate.now()
        return workoutSessions.filter { it.date.toLocalDate() == today }
    }
    
    override suspend fun getRecentWorkouts(limit: Int): List<WorkoutSession> {
        delay(100)
        return workoutSessions.sortedByDescending { it.date }.take(limit)
    }
    
    override suspend fun getWorkoutsByDate(date: LocalDate): List<WorkoutSession> {
        delay(100)
        return workoutSessions.filter { it.date.toLocalDate() == date }
    }
    
    override suspend fun getDailyStats(date: LocalDate): DailyStats {
        delay(100)
        val sessions = getWorkoutsByDate(date)
        return DailyStats(
            date = date,
            totalCalories = sessions.size * 100, // 임시 계산
            totalWorkoutTime = sessions.size * 30,
            workoutCount = sessions.size,
            completedSessions = sessions.filter { it.isCompleted }
        )
    }
    
    override suspend fun getMonthlyStats(year: Int, month: Int): MonthlyStats {
        delay(100)
        val monthSessions = workoutSessions.filter { 
            it.date.year == year && it.date.monthValue == month 
        }
        
        val dailyStatsMap = monthSessions
            .groupBy { it.date.dayOfMonth }
            .mapValues { (day, sessions) ->
                DailyStats(
                    date = LocalDate.of(year, month, day),
                    totalCalories = sessions.size * 100,
                    totalWorkoutTime = sessions.size * 30,
                    workoutCount = sessions.size,
                    completedSessions = sessions.filter { it.isCompleted }
                )
            }
        
        return MonthlyStats(
            year = year,
            month = month,
            totalWorkouts = monthSessions.size,
            totalCalories = monthSessions.size * 100,
            totalTime = monthSessions.size * 30,
            dailyStats = dailyStatsMap
        )
    }
}
