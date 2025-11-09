# NewsApp

This is a simple news application that displays a feed of articles from a remote API.

https://github.com/Sirelon/NewsApp/blob/main/Screen_recording_20251109_142523.mp4

## Architecture

The app follows the MVI (Model-View-Intent) architecture pattern, with a Repository layer for data handling.

- **MVI (Model-View-Intent):** The core presentation layer pattern.
    - **Model:** Represents the UI state.
    - **View:** The UI, built with Jetpack Compose, which observes state changes.
    - **Intent:** User actions that trigger state updates.
- **Repository:** Manages data operations. It abstracts the data sources from the rest of the app, providing a clean API for data access to the ViewModel.
- **Data Sources:** Provides the actual data. In this app, we have:
    - **Remote Source:** Fetches news articles from a network API using Ktor.
    - **Local Source:** Store previously fetched data data in memory.

## Libraries Used

This project utilizes a variety of libraries to implement modern Android development practices.

### UI
*   [**Jetpack Compose**](https://developer.android.com/jetpack/compose): A modern toolkit for building native Android UI.
*   [**Coil**](https://coil-kt.github.io/coil/): An image loading library for Android.

### Dependency Injection
*   [**Koin**](https://insert-koin.io/): A pragmatic lightweight dependency injection framework for Kotlin.

### Networking
*   [**Ktor**](https://ktor.io/): A framework for building asynchronous clients.

### Concurrency
*   [**KotlinX Coroutines**](https://kotlinlang.org/docs/coroutines-overview.html): For managing asynchronous operations.
