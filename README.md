# 🧠 Upendra's Quiz App – Android App

Welcome to **Upendra’s Quiz App** – a modern Android quiz application built with clean architecture and cutting-edge Android development tools. This app is designed for a seamless and interactive quiz experience with offline capabilities, personalized profile storage, and social sharing options.

---

## 🧩 About the Project

**Upendra’s Quiz App** is a fully-featured quiz application that leverages the latest Android technologies including MVVM, Jetpack Compose, Room, Retrofit, and Proto DataStore. Users can take quizzes, view scores, store their profile securely, and share results across platforms like WhatsApp and Telegram directly from the app icon.

---
[Watch the demo](https://github.com/upend123/Upendra-s-Quiz-App/releases/download/v1.0.0/quiz-demo-v1.gif)

---
## ⚙️ Technologies Used

- **Kotlin** (Primary programming language)
- **Jetpack Compose** (Modern declarative UI toolkit)
- **MVVM Architecture** (For clean separation of concerns)
- **Retrofit** (API integration)
- **Room Database** (Local data persistence)
- **Proto DataStore** (Typed, asynchronous data storage for user profiles)
- **Coroutines & Flow** (For asynchronous and reactive programming)
- **Shared ViewModel** (Efficient data sharing between screens)
- **Shimmer Effect** (For loading UI animation)
- **Lottie Animations** (Smooth animated illustrations)
- **Intent Sharing** (WhatsApp & Telegram support directly from app icon)

---

## 🚀 Features

✅ **Multiple Choice Quiz** – Select the correct answer and move to the next question.

✅ **Profile Storage using Proto DataStore** – Save user preferences and profile securely.

✅ **Offline Storage using Room DB** – Keep quiz records and score data locally.

✅ **Live Score Tracking** – Real-time quiz scoring system.

✅ **One-Time Score Save** – Prevents duplicate entries for final scores.

✅ **Shimmer UI Effect** – Beautiful loading indicators for better UX.

✅ **Lottie Animations** – Visually appealing transitions and effects.

✅ **WhatsApp & Telegram Message Sharing** – Share your results instantly from the app icon.

✅ **Shared ViewModel** – Enables data sharing across composables/screens.

✅ **Modern Jetpack Compose UI** – Fully responsive and elegant UI experience.

## 📂 Project Structure

```
📁 UpendrasQuizApp/
│
├── 📁 app/
│   ├── 📁 manifests/
│   │   └── 📄 AndroidManifest.xml            ← App-level configuration like launcher activity, permissions, etc.
│
│   └── 📁 java/com/example/upendrasquizapp/
│
│       ├── 📁 app/
│       │   ├── 📁 main_activity/
│       │   │   ├── 📄 MainActivity.kt        ← Entry point of the app using Jetpack Compose.
│       │   │   └── 📄 QuizApplication.kt     ← Custom Application class for initializing dependencies.
│
│       ├── 📁 profile_feature/              ← 📌 All logic related to user profile and preferences.
│       │   ├── 📁 common/
│       │   │   └── 📄 ShareOnAppIconButton.kt   ← Share profile content via WhatsApp/Telegram directly from app icon.
│       │   ├── 📁 data/
│       │   │   ├── 📁 di/
│       │   │   │   └── 📄 DataStoreModule.kt    ← Hilt module for Proto DataStore dependency injection.
│       │   │   └── 📁 repository/
│       │   │       └── 📄 UserPrefsProtoRepository.kt ← Manages user preferences using Proto DataStore.
│       │   ├── 📁 object/
│       │   │   └── 📄 UserProtoSerializer.kt ← Proto serializer class for storing structured profile data.
│       │   └── 📁 presentation/screens/profile/
│       │       ├── 📄 ProfileScreen.kt       ← UI for user profile.
│       │       ├── 📄 ProfileViewModel.kt    ← ViewModel with business logic for profile.
│       │       └── 📄 StateProfileScreen.kt  ← Holds UI state for ProfileScreen.
│
│       ├── 📁 quiz_feature/                 ← 📌 Handles fetching, storing, and displaying quiz data.
│       │   ├── 📁 common/
│       │   │   └── 📄 NetworkResponse.kt     ← Wrapper for handling success/error API states.
│       │   ├── 📁 data/                      ← Data Layer (local & network).
│       │   │   ├── 📁 di/
│       │   │   │   ├── 📄 DatabaseModule.kt  ← Room DB dependencies.
│       │   │   │   ├── 📄 DataModule.kt      ← API service provider.
│       │   │   │   └── 📄 NetworkModule.kt   ← Retrofit setup and API injection.
│       │   │   ├── 📁 local/
│       │   │   │   ├── 📁 dao/
│       │   │   │   │   └── 📄 QuizDao.kt     ← DAO interface for Room DB.
│       │   │   │   └── 📁 db/
│       │   │   │       ├── 📄 QuizDatabase.kt ← Room Database.
│       │   │   │       └── 📄 QuizDatabaseRepo.kt ← Data access repo abstraction for Room DB.
│
│       ├── 📁 presentation/                 ← 📌 All UI screens of the app.
│       │   ├── 📁 navigation/
│       │   │   ├── 📄 NavGraph.kt           ← Navigation graph using Jetpack Compose.
│       │   │   └── 📄 Routes.kt             ← Routes for screens used in navigation.
│       │   └── 📁 screens/
│       │       ├── 📁 common/               ← Reusable composables like buttons, app bars, etc.
│       │       │   ├── 📄 AppDropDownMenu.kt
│       │       │   ├── 📄 ButtonBox.kt
│       │       │   ├── 📄 noRippleClickable.kt
│       │       │   ├── 📄 QuizAppBar.kt
│       │       │   └── 📄 VibrateOnClick.kt
│       │
│       │       ├── 📁 home/                 ← Home screen with category list or options.
│       │       │   ├── 📁 component/
│       │       │   │   └── 📄 EventsHomeScreen.kt
│       │       │   ├── 📄 HomeScreen.kt
│       │       │   ├── 📄 HomeScreenViewModel.kt
│       │       │   └── 📄 StateHomeScreen.kt
│       │
│       │       ├── 📁 navigationDrawer/     ← Drawer UI with shimmer & error handling.
│       │       │   ├── 📄 DrawerItemErrorScreen.kt
│       │       │   ├── 📄 NavigationDrawer.kt
│       │       │   └── 📄 ShimmerDrawerItem.kt
│       │
│       │       ├── 📁 quiz/                 ← Quiz screen where questions are answered.
│       │       │   ├── 📁 component/
│       │       │   │   └── 📄 EventQuizScreen.kt
│       │       │   ├── 📄 QuizScreen.kt
│       │       │   ├── 📄 QuizViewModel.kt
│       │       │   └── 📄 StateQuizScreen.kt
│       │
│       │       └── 📁 score/                ← Final score display after quiz completion.
│       │           └── 📄 ScoreScreen.kt
│
│       ├── 📁 utils/
│       │   ├── 📄 Constants.kt              ← Common constants.
│       │   ├── 📄 dimens.kt                 ← Reusable dimensions.
│       │   └── 📄 usefulMethods.kt          ← Utility functions (like formatting or validation).
│
│       ├── 📁 ui/                           ← Reserved for shared UI (optional).
│
│       ├── 📁 data/                         ← Duplicate, can be merged or organized.
│       │   ├── dao/
│       │   │   └── 📄 QuizDao.kt
│       │   ├── db/
│       │   │   ├── 📄 QuizDatabase.kt
│       │   │   ├── 📄 QuizDatabaseRepo.kt
│       │   │   └── 📄 QuizDatabaseViewModel.kt
│       │   ├── entity/
│       │   │   ├── 📄 Converters.kt         ← TypeConverters for Room.
│       │   │   └── 📄 QuizEntity.kt         ← Data model for Room.
│       │   ├── mapper/
│       │   │   └── 📄 QuizMapper.kt         ← Converts API model ↔ DB entity ↔ domain model.
│       │   └── remote/repository/
│       │       └── 📄 QuizRepositoryImpl.kt ← Actual implementation of quiz repository.
│
│       ├── 📁 domain/                       ← Clean architecture domain layer.
│       │   ├── 📁 di/
│       │   │   └── 📄 DomainModule.kt       ← UseCase + repository injection setup.
│       │   ├── 📁 model/
│       │   │   └── 📄 Quiz.kt               ← Domain model.
│       │   └── 📁 repository/
│       │       └── 📄 QuizRepository.kt     ← Abstract interface for repository.
│
│       └── 📁 usecases/                     ← Business logic encapsulated.
│           ├── 📁 quizUseCase/
│           │   ├── 📁 quiz/
│           │   │   ├── 📄 DeleteQuizByIdUseCase.kt
│           │   │   ├── 📄 DeleteQuizUseCase.kt
│           │   │   ├── 📄 GetQuizzesByDBUseCase.kt
│           │   │   ├── 📄 GetQuizzesUseCases.kt
│           │   │   └── 📄 UpsertQuizUseCase.kt
│           │   └── 📁 wrapper/
│           │       └── 📄 QuizUseCases.kt   ← Aggregates all quiz use cases into a single class.
│
├── 📁 res/
│   ├── 📁 drawable/                         ← App icons, images.
│   ├── 📁 mipmap/                           ← Launcher icons.
│   ├── 📁 raw/
│   │   └── 📄 congrg.json                   ← 🎉 Lottie animation for congratulations.
│   ├── 📁 values/
│   │   └── 📄 strings.xml                   ← App-wide strings and localization.
│   └── 📁 xml/                              ← Configs like backups or file providers.
│
├── 📁 test/                                 ← Unit tests.
│   └── com/example/upendrasquizapp/
│       └── ...
│
├── 📁 androidTest/                          ← UI tests (Espresso/Compose).
│   └── com/example/upendrasquizapp/
│       └── ...
│
├── 📄 build.gradle
├── 📄 proguard-rules.pro
└── 📄 README.md                             ← Project overview and setup instructions.

```
### 📥 Direct APK Download:
Click below to download the latest version of the APK directly:

➡️ **[Download APK](https://github.com/upend123/Upendra-s-Quiz-App/releases/download/v1.0/UpendrasQuizApp-v1.0.apk)**

### 🚀 Running the App Locally (Android Studio):
1. Clone the repository:
   ```bash
   git clone https://github.com/upend123/Upendra-s-Quiz-App.git
   ```
2. Open the project in **Android Studio**.
3. Connect your Android device or start an emulator.
4. Click on **Run** ▶️ to install and launch the app.

---

## 📜 License
This project is licensed under the **MIT License** – you're free to use and modify it.

💡 **Suggestions & Contributions are Welcome!** Feel free to submit issues and pull requests to improve the project.

---

💡 **Developed by [Upendra Yadav]**
