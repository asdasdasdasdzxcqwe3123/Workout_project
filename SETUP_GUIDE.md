# 프로젝트 설정 가이드

## 🔧 초기 설정

### 1. Gradle Sync 실행 (매우 중요!)

현재 Page6Screen.kt 등에서 **"Unresolved reference"** 오류가 발생하는 이유는 **Gradle Sync가 아직 완료되지 않았기 때문**입니다.

#### Android Studio에서 Gradle Sync 방법:

```
방법 1: 자동 알림
- Android Studio를 열면 상단에 "Gradle files have changed..." 알림
- "Sync Now" 클릭

방법 2: 수동 실행
- File → Sync Project with Gradle Files
- 또는 상단 툴바의 🐘 (코끼리) 아이콘 클릭

방법 3: 단축키
- Windows/Linux: Ctrl + Shift + O
- Mac: Cmd + Shift + O
```

#### Gradle Sync가 완료되면:
✅ 모든 Compose 함수가 인식됨  
✅ androidx 패키지가 정상적으로 import됨  
✅ 빨간 밑줄 오류가 사라짐  

### 2. 프로젝트 구조 확인

```bash
# 프로젝트 루트에서
./gradlew build
```

성공하면 모든 의존성이 정상적으로 설정된 것입니다.

## 🚀 개발 환경 설정

### Android Studio 설정

#### 1. 파일 자동 동기화
```
File → Settings → Appearance & Behavior → System Settings
✅ Synchronize files on frame or editor tab activation
✅ Save files on frame deactivation
```

#### 2. Compose Preview 활성화
```
View → Tool Windows → Preview
```
- 각 Screen 파일을 열면 오른쪽에 Preview가 표시됨
- Preview 버튼(▶️)을 눌러 UI 확인

#### 3. 에뮬레이터 설정
```
Tools → Device Manager → Create Device
- Phone → Pixel 6 추천
- System Image → Android 13 (API 33) 이상
```

### Cursor 설정

#### 1. 프로젝트 열기
```
File → Open Folder → WorkoutProject 폴더 선택
```

#### 2. Kotlin 확장 설치 (선택)
- Cursor 확장 마켓에서 "Kotlin" 검색
- "Kotlin Language" 확장 설치

## 📝 작업 흐름

### 방법 1: Cursor 주도 개발 (추천)

```
1. Cursor에서 코드 작성
   └─> 저장 (Ctrl + S)
   
2. Android Studio로 Alt + Tab
   └─> 자동으로 파일 변경 감지
   └─> Compose Preview에서 UI 확인
   
3. 필요시 Run (Shift + F10)으로 에뮬레이터 실행
```

### 방법 2: Android Studio 주도 개발

```
1. Android Studio에서 코드 작성
   └─> Compose Preview로 실시간 확인
   
2. 복잡한 로직은 Cursor에서 AI 도움받기
```

## ⚠️ 자주 발생하는 문제

### 문제 1: "Unresolved reference" 오류

**증상**: androidx, Composable 등이 빨간 밑줄

**해결**:
```
1. Gradle Sync 실행
   File → Sync Project with Gradle Files
   
2. Clean Build
   Build → Clean Project
   Build → Rebuild Project
   
3. 캐시 초기화 (최후의 수단)
   File → Invalidate Caches / Restart
```

### 문제 2: Preview가 안 보임

**해결**:
```
1. Preview 창 열기
   View → Tool Windows → Preview
   
2. @Preview 어노테이션 확인
   @Preview(showBackground = true)
   @Composable
   fun MyScreenPreview() { ... }
   
3. Build 후 Preview 새로고침
   Preview 창의 새로고침 버튼 클릭
```

### 문제 3: 에뮬레이터가 느림

**해결**:
```
1. HAXM 설치 확인 (Intel CPU)
   Tools → SDK Manager → SDK Tools
   ✅ Intel x86 Emulator Accelerator (HAXM)
   
2. 에뮬레이터 설정 최적화
   - Cold Boot 대신 Quick Boot 사용
   - RAM 2GB 할당
   - Multi-Core CPU 활성화
   
3. 실제 기기 사용 (더 빠름)
   - USB 디버깅 활성화
   - 기기 연결 후 Run
```

### 문제 4: Gradle Sync 실패

**오류**: "Could not resolve dependencies"

**해결**:
```bash
# 터미널에서 실행
./gradlew clean
./gradlew build --refresh-dependencies
```

## 🎨 Compose Preview 활용

### Preview 작성 팁

```kotlin
// 기본 Preview
@Preview(showBackground = true)
@Composable
fun MyScreenPreview() {
    MyScreen()
}

// 다크모드 Preview
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MyScreenDarkPreview() {
    MyScreen()
}

// 다양한 화면 크기 Preview
@Preview(showBackground = true, device = Devices.PIXEL_4)
@Preview(showBackground = true, device = Devices.TABLET)
@Composable
fun MyScreenDevicePreview() {
    MyScreen()
}
```

## 🔍 디버깅 팁

### Logcat 사용
```kotlin
import android.util.Log

@Composable
fun MyScreen() {
    Log.d("MyScreen", "화면이 렌더링됨")
    // ...
}
```

### 컴포지션 디버깅
```kotlin
@Composable
fun MyScreen() {
    SideEffect {
        println("MyScreen이 재구성됨")
    }
}
```

## 📚 참고 자료

### Jetpack Compose
- [공식 문서](https://developer.android.com/jetpack/compose)
- [Compose 샘플](https://github.com/android/compose-samples)
- [Material 3 가이드](https://m3.material.io/)

### Kotlin
- [Kotlin 공식 문서](https://kotlinlang.org/docs/home.html)
- [Kotlin for Android](https://developer.android.com/kotlin)

## ✅ 체크리스트

프로젝트를 시작하기 전에 확인하세요:

- [ ] Android Studio 설치됨 (2023.1 이상)
- [ ] JDK 17 설치됨
- [ ] Gradle Sync 성공
- [ ] 에뮬레이터 생성 완료
- [ ] Cursor에서 프로젝트 열림
- [ ] Git 설정 완료
- [ ] 팀원과 역할 분담 확인

## 🆘 도움이 필요하면

1. **프로젝트 문서 확인**: PROJECT_STRUCTURE.md
2. **팀원 README**: 각 member 폴더의 README.md
3. **Android Studio 로그**: Logcat 확인
4. **팀 채팅방**: 문제 공유 및 해결

Happy Coding! 🚀

