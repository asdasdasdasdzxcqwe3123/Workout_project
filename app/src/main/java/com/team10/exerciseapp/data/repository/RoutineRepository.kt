package com.team10.exerciseapp.data.repository

import com.team10.exerciseapp.data.model.WorkoutRoutine
import com.team10.exerciseapp.data.model.Difficulty
import com.team10.exerciseapp.data.model.RoutineDay

/**
 * 운동 루틴 저장소 인터페이스
 * 의존성 역전 원칙(DIP): 구현체가 아닌 인터페이스에 의존
 */
interface RoutineRepository {
    /**
     * 모든 루틴 가져오기
     */
    suspend fun getAllRoutines(): List<WorkoutRoutine>
    
    /**
     * 난이도별 루틴 가져오기
     */
    suspend fun getRoutinesByDifficulty(difficulty: Difficulty): List<WorkoutRoutine>
    
    /**
     * 루틴 상세 정보 가져오기
     */
    suspend fun getRoutineById(id: Int): WorkoutRoutine?
    
    /**
     * 루틴의 일별 계획 가져오기
     */
    suspend fun getRoutineDays(routineId: Int): List<RoutineDay>
}
