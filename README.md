# 🏨 Luxuary Hotel (Mobile App)

**Luxuary Hotel** is a mobile hotel booking application built with **Kotlin + Jetpack Compose**, following clean architecture and MVVM principles. It allows users to sign up, browse rooms, make reservations, view feedback, and manage bookings — all from their Android device.

---

## 📲 Tech Stack

### 🧩 Frontend (Mobile)
- **Kotlin** – Native Android development
- **Jetpack Compose** – Modern declarative UI
- **MVVM Architecture** – Separation of concerns
- **Hilt** – Dependency injection
- **Retrofit** – REST API communication
- **SharedPreferences** – Session management and local token storage

### 🛠 Backend (used by the app)
- **Django** – REST API provider
- **Django REST Framework** – Serializers and API routing
- **PostgreSQL** – Relational database
- **Token Authentication** – Secure user sessions

> ⚠️ The backend is used only to serve data to the mobile app. This repository focuses on the Android application only.

---

## 💡 Features

- 🔐 Login and registration
- 🏨 View all available hotel rooms with details
- 📆 Make and cancel reservations
- 💬 View and post reviews
- 💳 Payment interface (UI only)
- 🧭 Smooth Compose Navigation
- 🌗 Light/Dark mode support (optional)

---

## 📁 Project Structure

presentation/
├── view/
│   ├── screens/        # Full screens like Home, Login, Room Detail
│   └── component/      # UI components like cards, buttons, etc.
├── viewmodel/          # ViewModels for each screen
└── navigation/         # Routes, navigation actions, NavHost

data/
├── model/              # API data models
│   └── utils/          # Helper functions and validators
└── remote/
    ├── AppApi.kt            # Retrofit interface for API endpoints
    ├── RetrofitClient.kt    # Retrofit instance and configuration
    └── SharedPrefs.kt       # Token and session manager


---

## ▶️ Getting Started

1. Open the project in **Android Studio**.
2. Connect a device or emulator.
3. Update the **base URL** in Retrofit to point to your backend.
4. Click **Run** to launch the app.

---

## 👨‍💻 Author

**Mohamed Ali Benouarzeg**  
Android Developer

- 📧 mohamedbenouarzeg1@gmail.com  
- 🔗 [GitHub](https://github.com/mhxben)  
- 📸 [Instagram](https://instagram.com/mhx.kt)  
- 💼 [LinkedIn](https://www.linkedin.com/in/mohamed-ali-benouarzeg-3b55582b2/)

---
