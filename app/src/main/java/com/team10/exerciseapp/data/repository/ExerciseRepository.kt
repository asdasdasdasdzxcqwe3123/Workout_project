package com.team10.exerciseapp.data.repository

import com.team10.exerciseapp.data.model.Exercise
import com.team10.exerciseapp.data.model.ExerciseCategory
import com.team10.exerciseapp.data.model.Difficulty

/**
 * 운동 데이터 저장소 인터페이스
 * 의존성 역전 원칙(DIP): 구현체가 아닌 인터페이스에 의존
 * 개방-폐쇄 원칙(OCP): 확장에는 열려있고 수정에는 닫혀있음
 */
interface ExerciseRepository {
    /**
     * 모든 운동 가져오기
     */
    suspend fun getAllExercises(): List<Exercise>
    
    /**
     * 카테고리별 운동 가져오기
     */
    suspend fun getExercisesByCategory(category: ExerciseCategory): List<Exercise>
    
    /**
     * 난이도별 운동 가져오기
     */
    suspend fun getExercisesByDifficulty(difficulty: Difficulty): List<Exercise>
    
    /**
     * 검색어로 운동 찾기
     */
    suspend fun searchExercises(query: String): List<Exercise>
    
    /**
     * ID로 운동 가져오기
     */
    suspend fun getExerciseById(id: Int): Exercise?
}
