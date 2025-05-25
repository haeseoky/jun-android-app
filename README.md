# Jun Android App 🌈

부드러운 파스텔 톤 디자인의 안드로이드 앱

## ✨ 특징

### 🎨 디자인
- **파스텔 톤 컬러**: 부드럽고 편안한 색상 팔레트
- **둥근 모서리**: 모든 UI 요소에 부드러운 곡선 적용
- **그라디언트 배경**: 핑크→라벤더→스카이 블루 그라디언트
- **머티리얼 디자인 3.0**: 최신 디자인 가이드라인 적용

### 🚀 기능
- **스플래시 화면**: 3초간 아름다운 인트로 화면과 애니메이션
- **네비게이션 드로어**: 부드러운 전환과 파스텔 색상
- **반응형 카드**: 섀도우와 둥근 모서리가 적용된 카드 레이아웃
- **부드러운 애니메이션**: 페이드 인/아웃 및 스케일 애니메이션

## 🛠️ 기술 스택

- **언어**: Kotlin
- **플랫폼**: Android (API 34+)
- **UI 프레임워크**: Material Components
- **아키텍처**: MVVM with Navigation Component
- **빌드 도구**: Gradle (Kotlin DSL)

## 📱 화면 구성

### 1. 스플래시 화면
- 파스텔 그라디언트 배경
- 중앙에 앱 아이콘 (200dp 원형)
- 로딩 애니메이션 (3개 점 순차 깜빡임)
- 3초 딜레이 후 메인 화면 전환

### 2. 메인 화면
- **홈**: 웰컴 카드와 기능 소개
- **갤러리**: 그리드 형태의 아이콘 카드들
- **슬라이드쇼**: 기능별 설명 카드와 액션 버튼

### 3. 네비게이션
- 파스텔 그라디언트 배경의 드로어
- 둥근 프로필 이미지
- 색상으로 구분된 메뉴 아이템

## 🎨 색상 팔레트

```kotlin
// Primary Colors
primary_main: #88C999 (파스텔 민트)
primary_light: #A8E6CF
primary_dark: #6BB77B

// Secondary Colors  
secondary_main: #9A8AFF (파스텔 라벤더)
secondary_light: #B8A9FF
secondary_dark: #7C6BFF

// Background Colors
background_light: #FAF8FF (아이보리)
surface_light: #FFFFFF

// Accent Colors
accent_coral: #FFB3B3
accent_yellow: #FFEB9C
accent_blue: #B3D9FF
```

## 🏗️ 프로젝트 구조

```
app/
├── src/main/
│   ├── java/com/ocean/jun/
│   │   ├── MainActivity.kt
│   │   ├── SplashActivity.kt
│   │   └── ui/
│   │       ├── home/
│   │       ├── gallery/
│   │       └── slideshow/
│   └── res/
│       ├── layout/
│       ├── values/
│       │   ├── colors.xml
│       │   └── themes.xml
│       ├── drawable/
│       └── mipmap-*/
```

## 🚀 설치 및 실행

1. **Clone the repository**
   ```bash
   git clone https://github.com/haeseoky/jun-android-app.git
   cd jun-android-app
   ```

2. **Android Studio에서 열기**
   - Android Studio 실행
   - "Open an Existing Project" 선택
   - 프로젝트 폴더 선택

3. **빌드 및 실행**
   ```bash
   ./gradlew assembleDebug
   ./gradlew installDebug
   ```

## 📋 요구사항

- **Android SDK**: API 34 이상
- **Android Studio**: Arctic Fox 이상
- **Gradle**: 8.10.2
- **Kotlin**: 최신 버전

## 🎯 앱 기능

### 스플래시 화면
- ✅ 3초 딜레이
- ✅ 로고 페이드인 애니메이션
- ✅ 스케일 애니메이션
- ✅ 로딩 점 애니메이션
- ✅ 부드러운 화면 전환

### UI/UX
- ✅ 파스텔 톤 색상
- ✅ 둥근 모서리 디자인
- ✅ 그라디언트 배경
- ✅ 머티리얼 컴포넌트
- ✅ 다크 모드 지원

## 🔮 향후 계획

- [ ] 더 많은 애니메이션 효과
- [ ] 사용자 설정 기능
- [ ] 테마 커스터마이징
- [ ] 더 많은 화면 추가

## 📄 라이센스

MIT License

## 👨‍💻 개발자

**haeseoky**
- GitHub: [@haeseoky](https://github.com/haeseoky)

---

❤️ **부드러운 경험을 시작하세요** ❤️