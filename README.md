# Contact Status App

This Android application allows users to set a custom status message that is automatically sent as an SMS to incoming calls that are missed. The app is built using Kotlin and the latest Jetpack Compose UI toolkit, with Hilt for dependency injection and ViewModel for managing UI-related data.

This Android app is built with Kotlin and utilizes the modern Jetpack Compose for the UI, follows the MVVM (Model-View-ViewModel) architectural pattern for clean code structure, and leverages Hilt for dependency injection.

## Features

- Set a custom status message.
- Toggle the activation state to enable/disable automatic SMS response.
- Respond to incoming calls with a custom message if the call is missed.

## Installation

To run this project, clone the repository and import it into Android Studio.

## git clone 
URL: https://github.com/YazanAesmael/agent_insights.git
Open Android Studio, go to File -> New -> Project from Version Control & paste the URL of the cloned repository.

## Prerequisites
Before you begin, ensure you have met the following requirements:

You have installed the latest version of Android Studio and the Android SDK.
You have a basic understanding of Kotlin and Android development.
You have a device or emulator running Android API level 21 (Lollipop) or higher.

## Setup
To set up the project:

Open the project in Android Studio.
Sync the project with Gradle files.
Build the project by clicking on Build -> Make Project.

## Usage
After installing the app, follow these steps:

Launch the app.
Enter a status message in the text field provided.
Toggle the switch to "Active" to enable the automatic SMS feature.
The app will now listen for incoming calls and if a call is missed, it will send the status message as an SMS to the caller.
Permissions
The app requires the following permissions:

READ_PHONE_STATE: To detect incoming calls.
SEND_SMS: To send SMS messages.
READ_CALL_LOG: To check for missed calls.
Make sure to grant these permissions in your device settings or when prompted by the app.

## Contact
Yazan Aesmael
