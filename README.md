# 📚 Bookify

A modern Android application for browsing and managing your personal book collection. Built with Kotlin and Material Design 3.

---

## 📋 Project Overview

**Bookify** is a mobile book browsing and management app for Android that lets users explore a catalog of books, search and filter titles, save favorites, and manage their account—all through a clean, fragment-based UI with bottom navigation.

| | |
|---|---|
| **Platform** | Android |
| **Language** | Kotlin |
| **Min SDK** | 28 |
| **Target SDK** | 35 |

---

## ✨ Key Features

| Feature | Description |
|--------|-------------|
| 🏠 **Home** | Discover featured and recent books with horizontal and grid layouts. |
| 📖 **Book Browsing** | Browse the full catalog with genre-based organization and rich book cards. |
| 🔍 **Search** | Find books by title, author, or genre. |
| ❤️ **Favorites** | Save books to a personal favorites list for quick access. |
| 👤 **User Account** | Sign in, register, and manage profile; account screen with settings. |
| 🔔 **Notifications** | Optional notifications for new releases or reading reminders (extensible). |

---

## 🛠 Technical Stack

- **Language:** Kotlin
- **UI:** Material Design 3, ViewBinding
- **Navigation:** Fragment-based with bottom navigation (Explore, Favourites, Menu)
- **Images:** Glide
- **Serialization:** Gson
- **Layout:** ConstraintLayout, ViewPager2 (e.g. onboarding)
- **Build:** Gradle (Kotlin DSL), Android Gradle Plugin

---

## 🏗 Architecture

The app follows a simple, maintainable structure:

- **Single-Activity + Fragments:** `MainActivity` hosts a bottom nav and swaps fragments (Explore, Favourites, Menu).
- **Dedicated Activities:** Splash, Onboarding, Login, Register, Account, and Book Detail run as separate activities for clear flows.
- **Data Layer:** In-memory repository (`BookRepository`) and shared app state (`AppData`) for users and favorites; designed to be replaced with a remote API or local DB later.

```
┌─────────────────┐     ┌──────────────────┐     ┌─────────────────┐
│  SplashActivity │ ──► │ OnboardingActivity│ ──► │  LoginActivity  │
└─────────────────┘     └──────────────────┘     │ RegisterActivity│
                                                  └────────┬────────┘
                                                           │
                                                           ▼
┌─────────────────────────────────────────────────────────────────────┐
│                         MainActivity                                  │
│  ┌─────────────┐  ┌─────────────────┐  ┌─────────────────────────┐  │
│  │   Explore   │  │   Favourites    │  │         Menu            │  │
│  │   Fragment  │  │    Fragment     │  │       Fragment          │  │
│  └─────────────┘  └─────────────────┘  └─────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────┘
```

---

## 📁 App Structure

```
app/src/main/
├── java/com/example/bookify/
│   ├── SplashActivity.kt          # Launcher & routing to onboarding/login/main
│   ├── OnboardingActivity.kt      # First-time onboarding
│   ├── LoginActivity.kt           # Sign in
│   ├── RegisterActivity.kt        # Sign up
│   ├── MainActivity.kt            # Host for bottom nav + fragments
│   ├── AccountActivity.kt         # User account & settings
│   ├── BookDetailActivity.kt      # Single book details
│   ├── ExploreFragment.kt         # Home / browse books
│   ├── FavouritesFragment.kt      # Saved favorites
│   ├── MenuFragment.kt            # Menu / profile entry
│   ├── Book.kt                    # Book data model
│   ├── AppData.kt                 # User, currentUser, favourites
│   ├── BookRepository.kt          # Book catalog (demo data)
│   ├── BookGridAdapter.kt         # Grid book list
│   ├── BookHorizontalAdapter.kt   # Horizontal book list
│   └── OnboardingAdapter.kt       # Onboarding ViewPager2
├── res/
│   ├── layout/                    # Activities & fragments
│   ├── values/                    # themes, colors, strings, dimens
│   ├── drawable/                  # Icons & shapes
│   └── menu/                      # Bottom nav menu
└── AndroidManifest.xml
```

---

## 🚀 Getting Started

### Prerequisites

- **Android Studio** (Ladybug or newer recommended)
- **JDK 11**
- **Android SDK** with API 28+ (min), 35 (target)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-org/bookify.git
   cd bookify
   ```

2. **Open in Android Studio**
   - File → Open → select the `bookify` project folder.
   - Let Gradle sync (auto or “Sync Project with Gradle Files”).

3. **Run the app**
   - Connect an emulator or device (API 28+).
   - Select the `app` run configuration and click **Run** (or `Shift+F10`).

### Demo credentials (if applicable)

- **Email:** `user@gmail.com`  
- **Password:** `123456`

---

## 📦 Core Data Models

### Book

```kotlin
data class Book(
    val id: String,
    val title: String,
    val author: String,
    val year: Int,
    val genres: List<String>,
    val description: String,
    val imageRes: Int
)
```

### User

```kotlin
data class User(
    val name: String,
    val email: String,
    val password: String
)
```

Favorites are stored as a set of book IDs (e.g. `"bk_1"`) in `AppData.favourites`. The current session user is held in `AppData.currentUser`.

---

## 🎨 Design Highlights

- **Material Design 3** — `Theme.Material3.DayNight.NoActionBar` for consistent, modern components and dark/light support.
- **Edge-to-edge** — Content and bottom nav respect system bars and insets.
- **Floating bottom navigation** — Card-style nav with Explore, Favourites, and Menu.
- **Book cards** — Grid and horizontal list items with cover image, title, author, and genre chips.
- **Onboarding** — ViewPager2 with step indicators and skip/next actions.
- **Splash** — Dedicated splash theme and routing to onboarding or main flow.

---

## 🔐 Authentication Flow

1. **Splash** — App launches from `SplashActivity`; decides next screen (e.g. first launch vs returning user).
2. **Onboarding** — Shown on first launch; skip or complete steps, then proceed to login/register.
3. **Login / Register** — User signs in or creates an account; credentials validated against `AppData` (demo).
4. **Main** — After successful auth, user is taken to `MainActivity` with Explore/Favourites/Menu.
5. **Account** — Accessed from Menu; `AccountActivity` shows profile and options (e.g. sign out).

Future versions can replace in-memory `AppData` with a backend or Firebase Auth without changing this flow.

---

## 🧪 Testing

- **Unit tests:** JUnit 4 in `app/src/test/` (e.g. `ExampleUnitTest.kt` — extend with repository and model tests).
- **Instrumented tests:** AndroidX Test (JUnit + Espresso) in `app/src/androidTest/` for UI flows (login, navigation, favorites).

Run unit tests:
```bash
./gradlew test
```

Run instrumented tests (device/emulator required):
```bash
./gradlew connectedAndroidTest
```

---

## 🔮 Future Enhancements

- [ ] Replace in-memory data with **REST API** or **Room** for books and user data.
- [ ] **Cloud sync** for favorites and reading progress across devices.
- [ ] **Push notifications** for new releases or reading reminders.
- [ ] **Reading lists** and progress tracking (e.g. “Currently reading”, “Want to read”).
- [ ] **Offline support** with local caching and sync.
- [ ] **Accessibility** improvements (TalkBack, larger touch targets, contrast).
- [ ] **Jetpack Compose** migration for new screens or full UI refresh.

---

<p align="center">
  <sub>Built with Kotlin & Material Design 3</sub>
</p>
