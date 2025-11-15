package com.team10.exerciseapp.data.model

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * 운동 세션 (한 번의 운동 기록)
 * 단일 책임 원칙(SRP): 운동 세션 정보만 담당
 */
data class WorkoutSession(
    val id: Int,
    val exercise: Exercise,
    val sets: List<WorkoutSet>,
    val date: LocalDateTime = LocalDateTime.now(),
    val isCompleted: Boolean = false
) {
    /**
     * 완료된 세트 수 계산
     */
    fun getCompletedSetsCount(): Int = sets.count { it.isCompleted }
    
    /**
     * 총 세트 수
     */
    fun getTotalSetsCount(): Int = sets.size
    
    /**
     * 총 볼륨 계산
     */
    fun getTotalVolume(): Int = sets.sumOf { it.calculateVolume() }
    
    /**
     * 날짜 포맷팅
     */
    fun getFormattedDate(): String {
        return date.format(DateTimeFormatter.ofPattern("yyyy년 M월 d일"))
    }
    
    /**
     * 시간 포맷팅
     */
    fun getFormattedTime(): String {
        return date.format(DateTimeFormatter.ofPattern("HH:mm"))
    }
}
