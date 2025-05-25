# Jun Android App 🌈

부드러운 파스텔 톤 디자인의 안드로이드 앱 - 갤러리 접근 권한 처리 완료

## ✨ 특징

### 🎨 디자인
- **파스텔 톤 컬러**: 부드럽고 편안한 색상 팔레트
- **둥근 모서리**: 모든 UI 요소에 부드러운 곡선 적용
- **그라디언트 배경**: 핑크→라벤더→스카이 블루 그라디언트
- **머티리얼 디자인 3.0**: 최신 디자인 가이드라인 적용

### 🚀 기능
- **스플래시 화면**: 3초간 아름다운 인트로 화면과 애니메이션
- **자동 권한 처리**: 앱 시작 시 갤러리 접근 권한 자동 요청
- **네비게이션 드로어**: 부드러운 전환과 파스텔 색상
- **반응형 카드**: 섀도우와 둥근 모서리가 적용된 카드 레이아웃
- **부드러운 애니메이션**: 페이드 인/아웃 및 스케일 애니메이션

### 🔐 권한 관리
- **자동 권한 체크**: 앱 시작 시 갤러리 접근 권한 확인
- **Android 버전별 대응**: API 33+ (READ_MEDIA_IMAGES) / API 32- (READ_EXTERNAL_STORAGE)
- **사용자 친화적 안내**: 권한 승인/거부 시 명확한 피드백 제공
- **권한 유틸리티**: 재사용 가능한 권한 관리 클래스

## 🛠️ 기술 스택

- **언어**: Kotlin
- **플랫폼**: Android (API 34+)
- **UI 프레임워크**: Material Components
- **아키텍처**: MVVM with Navigation Component
- **빌드 도구**: Gradle (Kotlin DSL)
- **권한 처리**: ActivityResultContracts

## 📱 화면 구성

### 1. 스플래시 화면
- 파스텔 그라디언트 배경
- 중앙에 앱 아이콘 (200dp 원형)
- 로딩 애니메이션 (3개 점 순차 깜빡임)
- **권한 체크 및 요청**: 갤러리 접근 권한 자동 처리
- 3초 딜레이 후 메인 화면 전환

### 2. 메인 화면
- **홈**: 웰컴 카드와 기능 소개
- **갤러리**: 그리드 형태의 아이콘 카드들 (권한 확인 후 접근)
- **슬라이드쇼**: 기능별 설명 카드와 액션 버튼

### 3. 네비게이션
- 파스텔 그라디언트 배경의 드로어
- 둥근 프로필 이미지
- 색상으로 구분된 메뉴 아이템

## 🔐 권한 처리 시나리오

### 앱 최초 실행
1. **SplashActivity** 시작
2. **권한 체크**: 갤러리 접근 권한 확인
3. **권한 없음**: 1.5초 후 권한 요청 다이얼로그 표시
4. **권한 있음**: 3초 후 MainActivity로 자동 이동

### 권한 승인/거부 처리
- ✅ **승인**: "갤러리 접근 권한이 허용되었습니다" 토스트 표시
- ❌ **거부**: "갤러리 기능을 사용하려면 권한이 필요합니다" 안내 메시지

### Android 버전별 권한
```kotlin
// Android 13 (API 33) 이상
Manifest.permission.READ_MEDIA_IMAGES

// Android 12 (API 32) 이하  
Manifest.permission.READ_EXTERNAL_STORAGE
```

## 🏗️ 프로젝트 구조

```
app/
├── src/main/
│   ├── java/com/ocean/jun/
│   │   ├── MainActivity.kt (권한 상태 체크 예시)
│   │   ├── SplashActivity.kt (권한 요청 처리)
│   │   ├── WebViewActivity.kt
│   │   └── utils/
│   │       └── PermissionUtils.kt (권한 관리 유틸리티)
│   └── res/
│       ├── layout/
│       ├── values/
│       │   ├── colors.xml
│       │   ├── strings.xml (권한 관련 문자열)
│       │   └── themes.xml
│       ├── drawable/
│       └── mipmap-*/
└── AndroidManifest.xml (권한 선언)
```

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
   ./gradlew clean assembleDebug
   ./gradlew installDebug
   ```

## 📋 요구사항

- **Android SDK**: API 34 이상
- **Android Studio**: Arctic Fox 이상
- **Gradle**: 8.10.2
- **Kotlin**: 최신 버전
- **최소 지원 버전**: API 21 (Android 5.0)

## 🎯 구현된 기능

### 스플래시 화면
- ✅ 3초 딜레이
- ✅ 로고 페이드인 애니메이션
- ✅ 스케일 애니메이션
- ✅ 로딩 점 애니메이션  
- ✅ 부드러운 화면 전환
- ✅ **갤러리 권한 자동 요청**

### 권한 관리
- ✅ **Android 버전별 권한 처리**
- ✅ **ActivityResultLauncher 사용**
- ✅ **권한 상태 체크 유틸리티**
- ✅ **사용자 친화적 메시지**
- ✅ **권한 거부 시 앱 정상 동작**

### UI/UX
- ✅ 파스텔 톤 색상
- ✅ 둥근 모서리 디자인
- ✅ 그라디언트 배경
- ✅ 머티리얼 컴포넌트
- ✅ 다크 모드 지원

## 📝 권한 사용법

### PermissionUtils 클래스 활용
```kotlin
// 권한 체크
if (PermissionUtils.hasStoragePermissions(context)) {
    // 갤러리 접근 가능
} else {
    // 권한 없음 처리
}

// 권한 상태 확인 (디버깅용)
val status = PermissionUtils.getPermissionStatus(context)
```

### MainActivity에서 권한 체크 예시
```kotlin
binding.appBarMain.fab.setOnClickListener { view ->
    if (PermissionUtils.hasStoragePermissions(this)) {
        // 갤러리 기능 실행
    } else {
        // 권한 필요 안내
    }
}
```

## 🔮 향후 계획

- [ ] 카메라 권한 추가
- [ ] 설정 화면에서 권한 재요청 기능
- [ ] 더 많은 애니메이션 효과
- [ ] 사용자 설정 기능
- [ ] 테마 커스터마이징

## 📄 라이센스

MIT License

## 👨‍💻 개발자

**haeseoky**
- GitHub: [@haeseoky](https://github.com/haeseoky)

---

❤️ **부드러운 경험과 안전한 권한 관리를 시작하세요** ❤️
