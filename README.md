# NewsApp

This is a simple news application that displays a feed of articles from a remote API.

## Architecture

The app follows the MVI (Model-View-Intent) architecture pattern. This pattern helps to separate concerns and makes the code easier to manage and test.

- **Model:** Represents the state of the UI.
- **View:** A composable function that observes state changes and renders the UI.
- **Intent:** Represents user actions or events that can modify the state.



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