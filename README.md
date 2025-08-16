💧 Water Tracker - Daily Hydration Companion
A modern Android application built with Jetpack Compose to help you track your daily water intake.
Stay hydrated with a simple, intuitive interface, beautiful animations, and timely reminders.
This app is built following modern Android development principles, including a clean MVVM architecture,
dependency injection with Hilt, and a reactive data layer using Room and Kotlin Flows.

✨ Features : 

Log Water Intake: Tap on an animated water glass to quickly log a standard serving of water.

Smooth Animations: Enjoy a satisfying glass-filling animation powered by Lottie.

Intake History: View a timestamped list of every glass of water you've consumed throughout the day.

Hourly Reminders: Receive gentle, non-intrusive notifications every hour to remind you to stay hydrated.

Clean, Modern UI: A beautiful and simple user interface built entirely with Jetpack Compose.

Splash Screen: A seamless launch experience using the Android 12+ Splash Screen API.

🛠 Tech Stack & Architecture : 

This project serves as a demonstration of modern Android development practices and showcases a robust, scalable, and maintainable architecture.

Architecture:

MVVM (Model-View-ViewModel): A clean and scalable architectural pattern.

Clean Architecture Principles: Separation of concerns between UI, Domain, and Data layers.

Repository Pattern: Abstracting the data source from the rest of the app.

Tech Stack:

UI: Jetpack Compose - Android's modern, declarative UI toolkit.

Dependency Injection: Hilt - For managing dependencies and simplifying the app's structure.

Database: Room - A persistence library for local SQLite database storage.

Asynchronous Programming: Kotlin Coroutines & Flow - For managing background threads and handling data streams reactively.

Animation: Lottie for Compose - For rendering beautiful, high-quality animations.

Build Management: Gradle with Version Catalog - For centralized and scalable dependency management.

Code Generation: KSP (Kotlin Symbol Processing) - The modern, high-performance replacement for KAPT.