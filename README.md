# <img align="center" height="70" src="/assets/logo.png"/> Snowflake

A lightweight and customizable snowflake animation library for **Jetpack Compose Multiplatform**, supporting **Android**, **iOS**, **Desktop**, and **Web** platforms.

Add beautiful animated snow effects to your UI effortlessly.

<img src="assets/example.gif" alt="Demo" width="800" />

---

## ✨ Features

- Customizable snowflake **density** and **color**
- Works in any composable context: full-screen, background, or embedded
- Lightweight and efficient (uses particle reuse and optimized rendering)
- Pure Jetpack Compose code (no dependencies beyond Compose itself)
- Multiplatform ready: Android, iOS, Desktop, and Web

---

## 📦 Installation

Add this module as a dependency in your Compose Multiplatform project.

**Gradle (Kotlin DSL):**
```kotlin
dependencies {
    implementation("com.anasmusa:snowflake:1.0.0")
}
```
---

## 🧩 Usage

### Basic usage

```kotlin
Snowflake(
    modifier = Modifier.fillMaxSize(),
    density = 5, // 1 (low) to 10 (high)
    color = Color.White
)
```

### With content inside

```kotlin
SnowflakeBox(
    modifier = Modifier.fillMaxSize(),
    density = 7,
    color = Color.Cyan,
    contentAlignment = Alignment.Center
) {
    Text("Happy Holidays!", color = Color.White)
}
```

---

## 🛠️ API Reference

### `Snowflake`

Renders snowflake animation on a `Canvas`.

#### Parameters:
- `modifier`: `Modifier` – Compose modifier to control layout
- `density`: `Int` (1–10) – Number of snowflakes
- `color`: `Color` – Color of the snowflakes
- `contentDescription`: `String?` – Optional accessibility description

---

### `SnowflakeBox`

Renders snowflakes as a background behind any composable content.

#### Parameters:
- `modifier`: `Modifier`
- `density`: `Int` (1–10)
- `color`: `Color`
- `contentAlignment`: `Alignment`
- `propagateMinConstraints`: `Boolean`
- `content`: Composable lambda to provide inner UI

---

Inspired by the original snowflake animation from the [Telegram Android client](https://github.com/DrKLO/Telegram/blob/master/TMessagesProj/src/main/java/org/telegram/ui/Components/SnowflakesEffect.java), this version is adapted and rewritten for Compose Multiplatform.