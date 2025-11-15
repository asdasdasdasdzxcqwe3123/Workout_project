package com.team10.exerciseapp.data.model

/**
 * 운동 정보 데이터 모델
 * 단일 책임 원칙(SRP): 운동 정보만 담당
 */
data class Exercise(
    val id: Int,
    val name: String,
    val category: ExerciseCategory,
    val difficulty: Difficulty,
    val targetMuscle: String,
    val description: String = "",
    val imageUrl: String = "",
    val emoji: String = ""
)

/**
 * 운동 카테고리
 */
enum class ExerciseCategory(val displayName: String) {
    CHEST("가슴"),
    BACK("등"),
    LEGS("하체"),
    SHOULDERS("어깨"),
    ARMS("팔"),
    CORE("복근"),
    CARDIO("유산소"),
    FULL_BODY("전신")
}

/**
 * 난이도
 */
enum class Difficulty(val displayName: String, val color: String) {
    BEGINNER("초급", "#4CAF50"),
    INTERMEDIATE("중급", "#FF9800"),
    ADVANCED("고급", "#F44336")
}
