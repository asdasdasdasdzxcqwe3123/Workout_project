# 🏋️ Workout Management App

> 객체지향 프로그래밍 원칙을 적용한 Android 운동 루틴 관리 앱




### 핵심 특징
- ✅ **SOLID 원칙** 완벽 적용
- ✅ **MVVM 아키텍처** 패턴
- ✅ **Jetpack Compose** 선언적 UI
- ✅ **Repository 패턴**으로 데이터 추상화
- ✅ **StateFlow**를 통한 반응형 상태 관리
- ✅ **모듈화된 구조**로 팀 협업 최적화

---

## 🚀 주요 기능

### 6개의 핵심 페이지

| 페이지 | 기능 | 담당자 |
|--------|------|--------|
| **Page 1** | 홈 화면 (오늘의 운동, 통계, 최근 운동) | Member 1 |
| **Page 2** | 운동 루틴 목록 (난이도별 필터) | Member 3 |
| **Page 3** | 하체 운동 기록 (세트 기록) | Member 2 |
| **Page 4** | 운동 기록 캘린더 (월간 통계) | Member 2 |
| **Page 5** | 운동 백과사전 (부위별 검색) | Member 1 |
| **Page 6** | 루틴 상세 (5×5 스트렝스) | Member 3 |

---

## 🏗️ 아키텍처

### MVVM 패턴 적용

```
┌─────────────────────────────────────────┐
│           Presentation Layer            │
│  (UI - Jetpack Compose Screens)        │
└──────────────┬──────────────────────────┘
               │ observes StateFlow
┌──────────────▼──────────────────────────┐
│          ViewModel Layer                │
│  (State Management & Business Logic)   │
└──────────────┬──────────────────────────┘
               │ depends on
┌──────────────▼──────────────────────────┐
│         Repository Layer                │
│  (Data Access Abstraction)             │
└──────────────┬──────────────────────────┘
               │ uses
┌──────────────▼──────────────────────────┐
│           Data Layer                    │
│  (Models & Data Sources)               │
└─────────────────────────────────────────┘
```

---

## 🔗 공유 객체 및 클래스

6개 페이지가 공유하는 핵심 객체들:

### 📦 공유 데이터 모델
- `Exercise` - 운동 정보 (모든 페이지)
- `WorkoutSet` - 세트 정보 (Page 1, 3, 6)
- `WorkoutSession` - 운동 세션 (Page 1, 3, 4)
- `WorkoutRoutine` - 루틴 정보 (Page 1, 2, 6)
- `DailyStats` / `MonthlyStats` - 통계 (Page 1, 4)
- `RoutineDay` - 루틴 일별 계획 (Page 6)

### 🔄 공유 Repository
- `ExerciseRepository` - 운동 데이터 관리 (Page 1, 5)
- `WorkoutRepository` - 운동 기록 관리 (Page 1, 3, 4)
- `RoutineRepository` - 루틴 관리 (Page 2, 6)

### 🎨 공유 UI 컴포넌트
- `CommonTopBar` - 상단 바 (모든 페이지)
- `BottomNavigationBar` - 하단 네비게이션 (모든 페이지)
- 공통 색상 및 테마 (모든 페이지)

**📖 자세한 내용은 [SHARED_OBJECTS_GUIDE.md](SHARED_OBJECTS_GUIDE.md)를 참고하세요!**



## 📁 프로젝트 구조

```
app/src/main/java/com/team10/exerciseapp/
│
├── data/                           # 데이터 레이어
│   ├── model/                     # 공유 데이터 모델 ⭐
│   │   ├── Exercise.kt           # 모든 페이지에서 사용
│   │   ├── WorkoutSet.kt         # Page 1, 3, 6
│   │   ├── WorkoutSession.kt     # Page 1, 3, 4
│   │   ├── WorkoutRoutine.kt     # Page 1, 2, 6
│   │   ├── DailyStats.kt         # Page 1, 4
│   │   └── RoutineDay.kt         # Page 6
│   │
│   └── repository/                # 공유 Repository ⭐
│       ├── ExerciseRepository.kt      # Page 1, 5
│       ├── WorkoutRepository.kt       # Page 1, 3, 4
│       ├── RoutineRepository.kt       # Page 2, 6
│       └── impl/
│
├── ui/                            # UI 레이어
│   ├── common/                   # 공유 UI 컴포넌트 ⭐
│   │   └── CommonComponents.kt   # 모든 페이지에서 사용
│   │
│   ├── navigation/               # 공유 Navigation ⭐
│   │   └── AppNavigation.kt      # 모든 페이지 연결
│   │
│   ├── member1_dongho/           # 팀원 1
│   │   ├── page1/               # 홈
│   │   └── page5/               # 백과사전
│   │
│   ├── member2_junbeom/          # 팀원 2
│   │   ├── page3/               # 운동 기록
│   │   └── page4/               # 캘린더
│   │
│   └── member3_Seokjin/          # 팀원 3
│       ├── page2/               # 루틴 목록
│       └── page6/               # 루틴 상세
│
└── MainActivity.kt                # 앱 진입점
```

---

## 1. 공유 데이터 모델

모든 페이지가 공유하는 핵심 데이터 클래스들입니다.

### 📦 `Exercise.kt` - 운동 정보

```kotlin
data class Exercise(
    val id: Int,
    val name: String,              // 운동 이름
    val category: ExerciseCategory, // 카테고리 (가슴, 등, 하체, 팔)
    val difficulty: Difficulty,     // 난이도 (초급, 중급, 고급)
    val targetMuscle: String,       // 타겟 근육
    val emoji: String = "🏋️"       // 이모지
)

enum class ExerciseCategory(val displayName: String) {
    CHEST("가슴"), BACK("등"), LEGS("하체"), ARMS("팔")
}

enum class Difficulty(val displayName: String) {
    BEGINNER("초급"), INTERMEDIATE("중급"), ADVANCED("고급")
}
```

**사용하는 페이지:**
- ✅ Page 1 (홈): 최근 운동 표시
- ✅ Page 2 (루틴 목록): 루틴에 포함된 운동 표시
- ✅ Page 3 (운동 기록): 기록 중인 운동 정보
- ✅ Page 5 (백과사전): 운동 검색 및 필터링
- ✅ Page 6 (루틴 상세): 루틴의 운동 목록

---

### 📦 `WorkoutSet.kt` - 운동 세트 정보

```kotlin
data class WorkoutSet(
    val setNumber: Int,      // 세트 번호
    val weight: Int,         // 무게 (kg)
    val reps: Int,          // 반복 횟수
    val isCompleted: Boolean // 완료 여부
)
```

**사용하는 페이지:**
- ✅ Page 1 (홈): 오늘의 운동 세트 표시
- ✅ Page 3 (운동 기록): 세트별 무게/횟수 입력
- ✅ Page 6 (루틴 상세): 권장 세트 정보

---

### 📦 `WorkoutSession.kt` - 운동 세션

```kotlin
data class WorkoutSession(
    val id: Int,
    val date: LocalDate,                    // 운동 날짜
    val exercises: List<Exercise>,          // 수행한 운동 목록
    val totalDuration: Int,                 // 총 운동 시간 (분)
    val totalCalories: Int,                 // 소모 칼로리
    val isCompleted: Boolean = false        // 완료 여부
) {
    fun getFormattedDuration(): String {
        val hours = totalDuration / 60
        val minutes = totalDuration % 60
        return if (hours > 0) "${hours}시간 ${minutes}분" else "${minutes}분"
    }
}
```

**사용하는 페이지:**
- ✅ Page 1 (홈): 최근 운동 세션 표시
- ✅ Page 3 (운동 기록): 현재 진행 중인 세션
- ✅ Page 4 (캘린더): 날짜별 운동 세션 조회

---

### 📦 `WorkoutRoutine.kt` - 운동 루틴

```kotlin
data class WorkoutRoutine(
    val id: Int,
    val name: String,                    // 루틴 이름
    val description: String,             // 설명
    val difficulty: Difficulty,          // 난이도
    val durationWeeks: Int,             // 기간 (주)
    val daysPerWeek: Int,               // 주당 운동 일수
    val participantCount: Int = 0,      // 참여자 수
    val rating: Float = 0f              // 평점
) {
    fun getTotalDays(): Int = durationWeeks * daysPerWeek
}
```

**사용하는 페이지:**
- ✅ Page 1 (홈): 오늘의 추천 루틴
- ✅ Page 2 (루틴 목록): 전체 루틴 목록 표시
- ✅ Page 6 (루틴 상세): 선택한 루틴의 상세 정보

---

### 📦 `DailyStats.kt` & `MonthlyStats.kt` - 통계

```kotlin
data class DailyStats(
    val date: LocalDate,
    val workoutCount: Int,      // 운동 횟수
    val totalCalories: Int,     // 총 칼로리
    val totalTime: Int,         // 총 시간 (분)
    val hasWorkout: Boolean     // 운동 여부
)

data class MonthlyStats(
    val year: Int,
    val month: Int,
    val totalWorkouts: Int,     // 월간 총 운동 횟수
    val totalCalories: Int,     // 월간 총 칼로리
    val totalTime: Int,         // 월간 총 시간
    val dailyStats: List<DailyStats> = emptyList()
) {
    fun getWorkoutDays(): List<Int> {
        return dailyStats.filter { it.hasWorkout }.map { it.date.dayOfMonth }
    }
}
```

**사용하는 페이지:**
- ✅ Page 1 (홈): 오늘의 통계 표시
- ✅ Page 4 (캘린더): 월간 통계 및 캘린더 표시

---

### 📦 `RoutineDay.kt` - 루틴 일별 계획

```kotlin
data class RoutineDay(
    val routineId: Int,
    val dayNumber: Int,              // 일차 (Day 1, Day 2, ...)
    val title: String,               // 제목 (예: "상체 집중")
    val exercises: List<Exercise>,   // 운동 목록
    val sets: Int,                   // 세트 수
    val restTime: Int = 90          // 세트 간 휴식 시간 (초)
) {
    fun getExerciseNames(): String {
        return exercises.joinToString(", ") { it.name }
    }
}
```

**사용하는 페이지:**
- ✅ Page 6 (루틴 상세): 일별 운동 계획 표시

---

## 2. 공유 Repository

### 🔄 `ExerciseRepository` - 운동 데이터 관리

```kotlin
interface ExerciseRepository {
    suspend fun getAllExercises(): List<Exercise>
    suspend fun getExerciseById(id: Int): Exercise?
    suspend fun getExercisesByCategory(category: ExerciseCategory): List<Exercise>
    suspend fun searchExercises(query: String): List<Exercise>
}
```

**사용하는 ViewModel:**
- ✅ `Page1ViewModel` - 최근 운동 조회
- ✅ `Page5ViewModel` - 운동 검색 및 필터링

---

### 🔄 `WorkoutRepository` - 운동 기록 관리

```kotlin
interface WorkoutRepository {
    suspend fun getTodayWorkout(): WorkoutSession?
    suspend fun getRecentWorkouts(limit: Int): List<WorkoutSession>
    suspend fun saveWorkoutSession(session: WorkoutSession)
    suspend fun getMonthlyStats(year: Int, month: Int): MonthlyStats
}
```

**사용하는 ViewModel:**
- ✅ `Page1ViewModel` - 오늘의 운동 및 최근 운동
- ✅ `Page3ViewModel` - 운동 기록 저장
- ✅ `Page4ViewModel` - 월간 통계 조회

---

### 🔄 `RoutineRepository` - 루틴 관리

```kotlin
interface RoutineRepository {
    suspend fun getAllRoutines(): List<WorkoutRoutine>
    suspend fun getRoutineById(id: Int): WorkoutRoutine?
    suspend fun getRoutinesByDifficulty(difficulty: Difficulty): List<WorkoutRoutine>
    suspend fun getRoutineDays(routineId: Int): List<RoutineDay>
}
```

**사용하는 ViewModel:**
- ✅ `Page2ViewModel` - 루틴 목록 조회
- ✅ `Page6ViewModel` - 루틴 상세 정보 조회

---

## 3. 공유 UI 컴포넌트

### 🎨 `CommonComponents.kt`

```kotlin
// 공통 색상
val PrimaryBlue = Color(0xFF2196F3)
val LightGray = Color(0xFFF5F5F5)
val TextGray = Color(0xFF757575)

// 공통 TopBar
@Composable
fun CommonTopBar(
    title: String,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {}
)

// 공통 BottomNavigationBar
@Composable
fun BottomNavigationBar(
    currentRoute: String,
    onNavigate: (String) -> Unit
)

// 공통 버튼
@Composable
fun CommonButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
)
```

**사용하는 페이지:**
- ✅ **모든 페이지** - TopBar, BottomNavigationBar, 공통 색상

---

### 🧭 `AppNavigation.kt` - Navigation

```kotlin
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = "page1") {
        composable("page1") { Page1Screen(...) }
        composable("page2") { Page2Screen(...) }
        composable("page3") { Page3Screen(...) }
        composable("page4") { Page4Screen(...) }
        composable("page5") { Page5Screen(...) }
        composable("page6") { Page6Screen(...) }
    }
}
```

**모든 페이지가 공유하는 Navigation 시스템**

---

## 4. 페이지별 의존성 맵

### Page 1 (홈 화면)
```
Page1Screen
    ↓
Page1ViewModel
    ↓
├─ WorkoutRepository → WorkoutSession, DailyStats
└─ ExerciseRepository → Exercise
    ↓
공통 UI: CommonTopBar, BottomNavigationBar
```

### Page 2 (루틴 목록)
```
Page2Screen
    ↓
Page2ViewModel
    ↓
RoutineRepository → WorkoutRoutine, Difficulty
    ↓
공통 UI: CommonTopBar, BottomNavigationBar
```

### Page 3 (운동 기록)
```
Page3Screen
    ↓
Page3ViewModel
    ↓
WorkoutRepository → WorkoutSet, WorkoutSession
    ↓
공통 UI: BottomNavigationBar
```

### Page 4 (캘린더)
```
Page4Screen
    ↓
Page4ViewModel
    ↓
WorkoutRepository → MonthlyStats, DailyStats
    ↓
공통 UI: CommonTopBar, BottomNavigationBar
```

### Page 5 (백과사전)
```
Page5Screen
    ↓
Page5ViewModel
    ↓
ExerciseRepository → Exercise, ExerciseCategory
    ↓
공통 UI: CommonTopBar, BottomNavigationBar
```

### Page 6 (루틴 상세)
```
Page6Screen
    ↓
Page6ViewModel
    ↓
RoutineRepository → WorkoutRoutine, RoutineDay, Exercise
    ↓
공통 UI: BottomNavigationBar

## 🛠️ 설치 및 실행

### 요구사항
- Android Studio Hedgehog (2023.1.1) 이상
- Kotlin 1.9.0 이상
- 최소 SDK 24 (Android 7.0)
- 타겟 SDK 34 (Android 14)

### 실행 방법

1. **프로젝트 클론**
```bash
git clone https://github.com/your-username/workout-project.git
cd workout-project
```

2. **Android Studio에서 열기**
- `File → Open` → 프로젝트 폴더 선택

3. **Gradle Sync**
- Android Studio가 자동으로 Gradle Sync 수행
- 또는 `File → Sync Project with Gradle Files`

4. **앱 실행**
- 에뮬레이터 또는 실제 기기 연결
- `Run → Run 'app'` (Shift + F10)

### 화면 미리보기

**Compose Preview 사용:**
- 각 `Page*Screen.kt` 파일 열기
- 파일 우측에 Preview 창 자동 표시
- 코드 수정 시 실시간 업데이트

---

## 👥 팀원 및 역할

| 팀원 | 담당 페이지 | 역할 |
|------|------------|------|
| **Member 1 (dongho)** | Page 1, Page 5 | 홈 화면, 운동 백과사전 |
| **Member 2 (junbeom)** | Page 3, Page 4 | 운동 기록, 캘린더 |
| **Member 3 (Seokjin)** | Page 2, Page 6 | 루틴 목록, 루틴 상세 |

---

## 📚 참고 문서

- [OOP_IMPLEMENTATION_SUMMARY.md](OOP_IMPLEMENTATION_SUMMARY.md) - 객체지향 구현 요약
- [SHARED_OBJECTS_GUIDE.md](SHARED_OBJECTS_GUIDE.md) - 공유 객체 상세 가이드
- [SCREEN_PREVIEW_GUIDE.md](SCREEN_PREVIEW_GUIDE.md) - 화면 미리보기 방법

---

## 📝 License

This project is licensed under the MIT License.

---

## 🙏 Acknowledgments

- Figma 디자인 기반 구현
- Jetpack Compose 공식 문서
- Android Architecture Components

---

**Made with ❤️ by Team 10**
