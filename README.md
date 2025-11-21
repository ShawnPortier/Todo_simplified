📝 To-Do List App (MVVM) — Student Improvements
📌 Overview

This To-Do List application is based on an Android Studio project created by Dr. Abdelrahman Elfikky (also known as Abdo), my Mobile Application Development instructor at the University of Arkansas at Little Rock.
Dr. Elfikky developed this project as a clean, simple example of proper MVVM architecture, Room database usage, and separation of concerns for Android development.

He generously provided the full source code to the class along with this announcement:

*“Attached is the ZIP file containing the Android Studio project for the To-Do List app we discussed in lecture. I put a lot of effort into creating a simple, clean example that follows MVVM and proper separation of concerns. You can easily modify the data layer, ViewModel, or UI and immediately see the impact.

Feel free to work on this project instead of the original assignment (Rebuild & Improve the Tip Calculator App). Just make at least two meaningful improvements. This project can also serve as a reference or example, and I will continue adding features to it.”*
— Abdo

✨ My Improvements

For the assignment, I added two meaningful features to enhance functionality and user experience:

✅ 1. Task Priority Level

Each task now includes a priority indicator (e.g., Low, Medium, High) to help users organize tasks more effectively.

☑️ 2. Task Completion Checkbox

A checkbox has been added to each task item, allowing users to mark tasks as completed directly from the list.

*** Screenshot and GIF are located in the media file. ***

These features were carefully integrated into the existing MVVM structure by modifying:

The Room entity

The DAO and database schema

The ViewModel logic

The Composable UI to display and interact with the new features

🛠️ Technologies Used

Kotlin

Jetpack Compose

Room Database

ViewModel + LiveData

MVVM Architecture

🎯 Purpose

This project demonstrates:

Understanding of MVVM

Ability to extend an existing codebase

Integration of new UI and data-layer features

Clean architecture and separation of concerns

🙏 Acknowledgments

Special thanks to Dr. Abdelrahman “Abdo” Elfikky for providing the original project and for his guidance in Mobile Application Development.
