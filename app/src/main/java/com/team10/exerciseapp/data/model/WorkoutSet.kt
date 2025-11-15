package com.team10.exerciseapp.data.model

/**
 * 운동 세트 정보
 * 단일 책임 원칙(SRP): 세트 정보만 담당
 */
data class WorkoutSet(
    val setNumber: Int,
    val weight: Int,
    val reps: Int,
    val isCompleted: Boolean = false
) {
    /**
     * 세트의 총 볼륨 계산
     * 비즈니스 로직을 데이터 모델 내부에 캡슐화
     */
    fun calculateVolume(): Int = weight * reps
}
