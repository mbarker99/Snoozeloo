# Snoozeloo

Snoozeloo is a modern Android alarm application developed as part of the [Mobile Dev Campus Monthly Challenge](https://pl-coding.com) by Philipp Lackner for November 2024. It showcases up-to-date Android development practices using the latest Jetpack tools and Kotlin best practices.

## Features

- ⏰ Set, edit, and delete alarms
- 🎨 Clean and modern user interface using Jetpack Compose
- 🔔 Persistent alarm scheduling with Room and Alarmanager
- 🧭 Built with scalable architecture (MVVM)
- 🧪 Includes unit and UI testing support

## Technologies Used

### Language

- **Kotlin** — Primary language used for Android development.

### Architecture

- **MVVM (Model-View-ViewModel)** — Promotes separation of concerns and modular design.

### Android Jetpack Libraries

- **Jetpack Compose** — Declarative UI toolkit for Android.
- **Room** — SQLite database abstraction for local data persistence.
- **LiveData** — Lifecycle-aware observable data holder.
- **ViewModel** — Stores and manages UI-related data in a lifecycle-aware manner.
- **AlarmManager** — Manages background tasks like alarm scheduling.
- **Navigation Compose** — Simplifies navigation between composables.

### Dependency Injection

- **Hilt** — Android's recommended DI library built on top of Dagger.

### Build System

- **Gradle with Kotlin DSL** — Configures build tasks and manages dependencies.

## Getting Started

1. **Clone the repository**

   ```bash
   git clone https://github.com/mbarker99/Snoozeloo.git
