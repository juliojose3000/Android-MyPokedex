# MyPokedex App

This project is imcomplete, however I will be adding new updates from time to time

MyPokedex is an Android application built to browse and discover Pokémon. It leverages modern Android development tools and practices to provide a smooth and informative user experience.

<!-- Optional: Add a screenshot or GIF of your app in action -->
<!-- ![App Screenshot](./path/to/your/screenshot.gif) -->
<!-- Or: -->
<!-- <p align="center">
  <img src="./path/to/your/screenshot.png" alt="App Screenshot" width="300"/>
</p> -->


## 🌟 Features

*   Browse a list of Pokémon.
*   View details for each Pokémon (e.g., name, type, stats - *Adjust as per your features*).
*   Search/Filter Pokémon (*If implemented*).
*   Offline Caching: Access previously loaded Pokémon data even when offline (*If implemented with Room*).
*   Clean, Material You-inspired UI built with Jetpack Compose.

## 🛠️ Built With

*   **[Kotlin](https://kotlinlang.org/)**: First-party and official programming language for Android development.
*   **[Jetpack Compose](https://developer.android.com/jetpack/compose)**: Android’s modern toolkit for building native UI.
*   **[Android Architecture Components](https://developer.android.com/topic/libraries/architecture)**:
    *   **[ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)**: Manages UI-related data in a lifecycle-conscious way.
    *   **[StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow)**: Observable data holder class.
    *   **[Navigation Component](https://developer.android.com/guide/navigation)**: For handling navigation between screens.
*   **[Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)**: For asynchronous programming.
*   **[Hilt](https://dagger.dev/hilt/)**: For dependency injection.
*   **Networking**:
    *   **[GraphQL](https://graphql.org/)**: Query language for your API.
    *   **[Apollo Android Client](https://www.apollographql.com/docs/android/)**: A type-safe GraphQL client for Android.

## ⚙️ Getting Started

### Prerequisites

*   Android Studio Iguana | 2023.2.1 or newer (or your current version).
*   Android SDK API Level 26 or higher (adjust as per your `minSdk`).
*   A GraphQL Pokémon API Endpoint (e.g., [PokeAPI GraphQL Wrapper](https://graphql-pokeapi.graphcdn.app/) or your own).
    *   *Note: If you are using a public API, mention it. If it requires an API key, explain how to get one and where to put it (e.g., in `local.properties` and accessed via `BuildConfig`).*
