# JitsiMeetAndroidApp
## Jitsi Meet

Jitsi Meet is a Kotlin Jetpack Compose application designed for online video conferencing with Firebase authentication. Users can log in, register, join conference rooms, and manage favorite conference room users.

### Key Features

1. **Welcome/Login/Register Page:**
   - Single page for user authentication.
   - Firebase handles secure login and registration.

2. **Conference Room Joining:**
   - Join rooms by entering the room name.
   - Integrates with Jitsi Meet for video conferencing.

3. **Favorites Management:**
   - Manage favorite conference room users.
   - View, add, and delete favorite users with swipe-to-dismiss functionality.

4. **Database Integration:**
   - Room database stores favorite users' room names for persistent access.

### Activities

1. **WelcomeActivity/LoginActivity/RegisterActivity:**
   - Combined welcome, login, and register page.
   - Handles user authentication via Firebase.

2. **ConferenceRoomJoinActivity:**
   - Authenticated users can join conference rooms by entering a room name.

3. **FavoritesActivity:**
   - Displays and manages favorite conference room users.
   - Swipe-to-delete functionality for removing users.

### Additional Features

1. **Intents:**
   - Utilized for navigation between screens and activities.

2. **Hardware Sensors:**
   - Accesses camera and microphone for video conferencing.

3. **Internet Connectivity:**
   - Required for user authentication and video conferencing.
   - Ensures stable network connectivity.

4. **Swipe-to-Delete Functionality:**
   - Allows users to swipe left on a favorite user's name to delete them.

### Issues

1. **Jitsi Sign-In Requirement:**
   - First user must sign in via browser.
   - Workarounds: JSON Web Tokens (JWT) with Firebase (paid service) or Google sign-in via browser.

### Future Enhancements

1. **Enhanced UI/UX:**
   - Implement improved UI designs and animations.

2. **User Profiles:**
   - Allow users to personalize their experience and manage settings.

3. **Notifications:**
   - Implement push notifications for upcoming meetings and new messages.

4. **Security Enhancements:**
   - Include end-to-end encryption for video conferences.

### Working App Pics and Video

#### Screenshots

| Welcome/Login Screen | Register Screen |
| :---: | :---: |
| ![Screenshot_20240513_044350](https://github.com/Devil-Anmol/JitsiMeet/assets/108612802/ec01f8f0-f35c-4d07-b425-5f19c4e8cc5d) | ![Screenshot_20240513_044432](https://github.com/Devil-Anmol/JitsiMeet/assets/108612802/1f88c5b4-d08a-4b04-8287-927a6b74a17f) |

| Join Conference Screen | Favorites Screen |
| :---: | :---: |
| ![Screenshot_20240513_045210](https://github.com/Devil-Anmol/JitsiMeet/assets/108612802/8dd99f6c-2f5b-4fc0-a6eb-24a9aa5d53cb) | ![Screenshot_20240513_045528](https://github.com/Devil-Anmol/JitsiMeet/assets/108612802/40f0d824-32a2-4490-8068-a8a19bf3251e) |

| Get Favorite Screen | Add New Favorites Screen |
| :---: | :---: |
| ![Screenshot_20240513_045600](https://github.com/Devil-Anmol/JitsiMeet/assets/108612802/b736a0ab-728e-4251-9b99-6d60aeb559ac) | ![Screenshot_20240513_045616](https://github.com/Devil-Anmol/JitsiMeet/assets/108612802/95b78e2d-3b13-47be-ba8d-8e0fc60c8e3e) |

#### Video
[Watch Video](https://drive.google.com/file/d/19b-AF9XMCfX_Izm2AbpO-gyWG61JsiA-/view?usp=sharing)

### Links

- [Jitsi Meet](https://jitsi.org/jitsi-meet/)
- [Jitsi Documentation](https://jitsi.github.io/handbook/docs/dev-guide/dev-guide-android-sdk)
- [Firebase](https://firebase.google.com/)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [JWT](https://jwt.io/)
