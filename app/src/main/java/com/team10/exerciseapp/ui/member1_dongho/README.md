# 팀원 1 담당 페이지

## 담당 화면
- **Page 1**: 홈 화면 (오늘의 운동, 통계, 최근 운동)
- **Page 5**: (Figma 디자인 확인 필요)

## 작업 가이드
1. Figma 디자인 확인: https://www.figma.com/design/Y8UmZfJZbGsByy8QfgZudX/Untitled?node-id=0-1
2. 각 페이지의 Screen.kt 파일에서 UI 구현
3. 필요시 ViewModel.kt 추가
4. 공통 컴포넌트는 `ui/common/` 폴더 사용

## 폴더 구조
```
member1/
├── page1/
│   ├── Page1Screen.kt        # 화면 UI
│   └── Page1ViewModel.kt     # 비즈니스 로직 (필요시)
└── page5/
    ├── Page5Screen.kt
    └── Page5ViewModel.kt
```

