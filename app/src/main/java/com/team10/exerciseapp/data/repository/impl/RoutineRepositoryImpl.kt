package com.team10.exerciseapp.data.repository.impl

import com.team10.exerciseapp.data.model.*
import com.team10.exerciseapp.data.repository.RoutineRepository
import kotlinx.coroutines.delay

/**
 * 운동 루틴 저장소 구현체
 * 단일 책임 원칙(SRP): 루틴 데이터 관리만 담당
 */
class RoutineRepositoryImpl : RoutineRepository {
    
    // 더미 운동 데이터
    private val exercises = listOf(
        Exercise(1, "스쿼트", ExerciseCategory.LEGS, Difficulty.BEGINNER, "하체", "", "", "🏋️"),
        Exercise(2, "벤치프레스", ExerciseCategory.CHEST, Difficulty.BEGINNER, "가슴", "", "", "💪"),
        Exercise(3, "데드리프트", ExerciseCategory.BACK, Difficulty.ADVANCED, "등", "", "", "💪"),
        Exercise(4, "오버헤드 프레스", ExerciseCategory.SHOULDERS, Difficulty.INTERMEDIATE, "어깨", "", "", "💪"),
        Exercise(5, "바벨 로우", ExerciseCategory.BACK, Difficulty.INTERMEDIATE, "등", "", "", "💪")
    )
    
    // 더미 루틴 데이터
    private val routines = listOf(
        WorkoutRoutine(
            id = 1,
            name = "5×5 스트렝스",
            description = "기초 근력 향상을 위한 클래식한 5×5 프로그램입니다.",
            difficulty = Difficulty.BEGINNER,
            exercises = listOf(exercises[0], exercises[1], exercises[2]),
            targetSets = 5,
            targetReps = 5,
            estimatedDuration = 45,
            participantCount = 12349,
            rating = 4.8f
        ),
        WorkoutRoutine(
            id = 2,
            name = "3분할 루틴",
            description = "가슴/등/어깨를 나눠 집중적으로 운동하는 프로그램입니다.",
            difficulty = Difficulty.INTERMEDIATE,
            exercises = listOf(exercises[1], exercises[2], exercises[3]),
            targetSets = 3,
            targetReps = 10,
            estimatedDuration = 60,
            participantCount = 8924,
            rating = 4.7f
        ),
        WorkoutRoutine(
            id = 3,
            name = "풀바디 워크아웃",
            description = "전신을 골고루 발달시키는 균형잡힌 프로그램입니다.",
            difficulty = Difficulty.ADVANCED,
            exercises = listOf(exercises[0], exercises[1], exercises[2], exercises[3], exercises[4]),
            targetSets = 4,
            targetReps = 8,
            estimatedDuration = 90,
            participantCount = 5678,
            rating = 4.6f
        )
    )
    
    // 5x5 스트렝스 루틴의 일별 계획
    private val routine5x5Days = listOf(
        RoutineDay(
            dayNumber = 1,
            title = "가슴 + 삼두",
            exercises = listOf(
                Exercise(1, "벤치프레스", ExerciseCategory.CHEST, Difficulty.BEGINNER, "가슴", "", "", "💪"),
                Exercise(2, "인클라인 프레스", ExerciseCategory.CHEST, Difficulty.BEGINNER, "가슴", "", "", "💪"),
                Exercise(3, "딥스", ExerciseCategory.CHEST, Difficulty.INTERMEDIATE, "가슴", "", "", "💪"),
                Exercise(4, "덤벨 플라이", ExerciseCategory.CHEST, Difficulty.BEGINNER, "가슴", "", "", "💪")
            ),
            sets = 5,
            reps = 5,
            description = "가슴과 삼두근을 집중적으로 단련하는 날입니다."
        ),
        RoutineDay(
            dayNumber = 2,
            title = "등 + 이두",
            exercises = listOf(
                Exercise(5, "데드리프트", ExerciseCategory.BACK, Difficulty.ADVANCED, "등", "", "", "💪"),
                Exercise(6, "바벨 로우", ExerciseCategory.BACK, Difficulty.INTERMEDIATE, "등", "", "", "💪"),
                Exercise(7, "풀업", ExerciseCategory.BACK, Difficulty.INTERMEDIATE, "등", "", "", "💪")
            ),
            sets = 5,
            reps = 5,
            description = "등과 이두근을 발달시키는 날입니다."
        ),
        RoutineDay(
            dayNumber = 3,
            title = "하체 + 어깨",
            exercises = listOf(
                Exercise(8, "스쿼트", ExerciseCategory.LEGS, Difficulty.BEGINNER, "하체", "", "", "🏋️"),
                Exercise(9, "레그프레스", ExerciseCategory.LEGS, Difficulty.BEGINNER, "하체", "", "", "🦵"),
                Exercise(10, "숄더프레스", ExerciseCategory.SHOULDERS, Difficulty.INTERMEDIATE, "어깨", "", "", "💪"),
                Exercise(11, "레터럴 레이즈", ExerciseCategory.SHOULDERS, Difficulty.BEGINNER, "어깨", "", "", "💪")
            ),
            sets = 5,
            reps = 5,
            description = "하체와 어깨를 강화하는 날입니다."
        )
    )
    
    override suspend fun getAllRoutines(): List<WorkoutRoutine> {
        delay(100)
        return routines
    }
    
    override suspend fun getRoutinesByDifficulty(difficulty: Difficulty): List<WorkoutRoutine> {
        delay(100)
        return routines.filter { it.difficulty == difficulty }
    }
    
    override suspend fun getRoutineById(id: Int): WorkoutRoutine? {
        delay(100)
        return routines.find { it.id == id }
    }
    
    override suspend fun getRoutineDays(routineId: Int): List<RoutineDay> {
        delay(100)
        // 현재는 5x5 루틴만 상세 정보 제공
        return if (routineId == 1) routine5x5Days else emptyList()
    }
}
