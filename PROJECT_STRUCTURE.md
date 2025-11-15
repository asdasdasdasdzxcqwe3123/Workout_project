# 운동 앱 프로젝트 구조 가이드

## 📱 프로젝트 개요
Figma 디자인 기반 운동 관리 Android 앱
- **개발 도구**: Android Studio + Cursor
- **언어**: Kotlin
- **UI 프레임워크**: Jetpack Compose
- **팀원**: 3명

## 🎨 Figma 디자인
https://www.figma.com/design/Y8UmZfJZbGsByy8QfgZudX/Untitled?node-id=0-1

## 👥 팀원별 담당 페이지

### 팀원 1
- **Page 1**: 홈 화면 (오늘의 운동, 통계, 최근 운동)
- **Page 5**: TBD

### 팀원 2
- **Page 3**: TBD
- **Page 4**: TBD

### 팀원 3
- **Page 2**: 운동 선택 화면 (난이도별 필터)
- **Page 6**: TBD

## 📁 프로젝트 폴더 구조

```
app/src/main/java/com/team10/exerciseapp/
├── MainActivity.kt                    # 앱 진입점
│
├── ui/
│   ├── theme/                        # 테마 설정 (색상, 타이포그래피 등)
│   │   ├── Color.kt
│   │   ├── Theme.kt
│   │   └── Type.kt
│   │
│   ├── navigation/                   # 화면 전환 관리
│   │   └── AppNavigation.kt         # 네비게이션 라우팅
│   │
│   ├── common/                       # 공통 컴포넌트
│   │   └── CommonComponents.kt      # 버튼, 헤더 등 공유 UI
│   │
│   ├── member1/                      # 팀원 1 담당
│   │   ├── README.md                # 작업 가이드
│   │   ├── page1/
│   │   │   ├── Page1Screen.kt      # Page 1 UI
│   │   │   └── Page1ViewModel.kt   # Page 1 로직 (필요시)
│   │   └── page5/
│   │       ├── Page5Screen.kt
│   │       └── Page5ViewModel.kt
│   │
│   ├── member2/                      # 팀원 2 담당
│   │   ├── README.md
│   │   ├── page3/
│   │   │   ├── Page3Screen.kt
│   │   │   └── Page3ViewModel.kt
│   │   └── page4/
│   │       ├── Page4Screen.kt
│   │       └── Page4ViewModel.kt
│   │
│   └── member3/                      # 팀원 3 담당
│       ├── README.md
│       ├── page2/
│       │   ├── Page2Screen.kt
│       │   └── Page2ViewModel.kt
│       └── page6/
│           ├── Page6Screen.kt
│           └── Page6ViewModel.kt
│
└── data/                             # 데이터 관련 (추후 추가)
    ├── model/                        # 데이터 모델
    ├── repository/                   # 데이터 저장소
    └── api/                          # API 통신 (필요시)
```

## 🔧 개발 워크플로우

### 1. Cursor에서 코드 작성
```kotlin
// 예: Page1Screen.kt 파일 수정
@Composable
fun Page1Screen() {
    // Figma 디자인 구현
}
```

### 2. Android Studio에서 확인
- 파일 저장 (Ctrl+S)
- Android Studio로 전환 (자동 동기화)
- Compose Preview 또는 Run으로 확인

### 3. Git으로 협업
```bash
# 작업 전
git pull origin main

# 작업 후
git add .
git commit -m "작업 내용"
git push origin main
```

## 📝 코딩 규칙

### 파일명 규칙
- Screen 파일: `PageXScreen.kt`
- ViewModel: `PageXViewModel.kt`
- Components: `PageXComponents.kt` (페이지별 컴포넌트)

### 주석 작성
```kotlin
/**
 * 함수/클래스 설명
 * @param 파라미터 설명
 */
@Composable
fun MyComponent(param: String) {
    // 구현 로직 주석
}
```

### Preview 작성
모든 Screen과 주요 Component에 Preview 추가:
```kotlin
@Preview(showBackground = true)
@Composable
fun MyScreenPreview() {
    MyScreen()
}
```

## 🚀 시작하기

### 1. 프로젝트 클론
```bash
git clone https://github.com/asdasdasdasdzxcqwe3123/Workout_project.git
cd Workout_project
```

### 2. Android Studio에서 프로젝트 열기
- `File → Open` → 프로젝트 폴더 선택
- Gradle Sync 완료 대기

### 3. Cursor에서 프로젝트 열기
- 같은 폴더를 Cursor로 열기
- 두 IDE를 동시에 사용

### 4. 에뮬레이터 실행
- Android Studio에서 에뮬레이터 실행
- 또는 실제 기기 연결

### 5. 개발 시작!
- 자신의 담당 페이지 폴더로 이동
- README.md 확인
- Screen.kt 파일 수정 시작

## 📚 참고 자료

### Jetpack Compose
- [공식 문서](https://developer.android.com/jetpack/compose)
- [Compose 레이아웃](https://developer.android.com/jetpack/compose/layouts)

### Navigation Compose
- [Navigation 가이드](https://developer.android.com/jetpack/compose/navigation)

### Material3
- [Material Design 3](https://m3.material.io/)

## ❓ 자주 묻는 질문

### Q: 다른 팀원의 페이지를 수정해야 할 때?
A: 먼저 팀원과 상의 후, Git 브랜치를 따로 만들어 작업

### Q: 공통 컴포넌트가 필요할 때?
A: `ui/common/CommonComponents.kt`에 추가하고 팀원들에게 공유

### Q: Figma 디자인이 변경되었을 때?
A: 각자 담당 페이지의 Screen.kt 파일만 수정

## 🐛 문제 해결

### Gradle Sync 실패
```bash
./gradlew clean
./gradlew build
```

### 파일 동기화 안 됨
- Android Studio: `File → Invalidate Caches / Restart`
- Cursor: 프로젝트 재시작

## 📞 연락
문제가 있으면 팀 채팅방에 문의하세요!

