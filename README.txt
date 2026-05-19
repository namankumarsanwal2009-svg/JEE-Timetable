# JEE Timetable - Android App

## How to Build the APK

### Step 1 - Install Android Studio
Download from: https://developer.android.com/studio
Install it (free). During setup, let it install the Android SDK automatically.

### Step 2 - Open the Project
1. Open Android Studio
2. Click "Open" (not "New Project")
3. Navigate to this folder (JEETimetable) and click OK
4. Wait for Gradle to sync (bottom status bar shows progress, takes 2-5 min first time)

### Step 3 - Build the APK
1. In the top menu: Build → Build Bundle(s) / APK(s) → Build APK(s)
2. Wait for build to finish (1-2 min)
3. A popup appears: click "locate" to find the APK
4. APK is at: app/build/outputs/apk/debug/app-debug.apk

### Step 4 - Install on OnePlus
Option A - USB:
1. Connect phone via USB
2. Enable "File Transfer" mode on phone
3. Copy the APK to your phone
4. Open it from Files app and tap Install

Option B - WhatsApp/Drive:
1. Send the APK to yourself on WhatsApp, or upload to Google Drive
2. Download it on your phone
3. Open and install

### Step 5 - Allow Unknown Apps (one time)
When installing, OnePlus will ask to allow installation from unknown sources.
Tap "Settings" → enable it → go back and tap Install.

---

## How the App Works

- Open the app once to schedule all alarms for the day
- The app uses Android's AlarmManager with setAlarmClock() - same system used by the Clock app
- At each slot time, your phone will:
  1. Wake the screen (even from lock screen)
  2. Play your phone's alarm ringtone at full volume, looping
  3. Vibrate in a repeating pattern
  4. Show a full-screen alarm overlay
  5. Show a persistent notification with "Stop Alarm" button
- Tap "Stop Alarm" on screen or notification to dismiss
- Alarms auto-reschedule daily when you open the app

## Slot Times
06:30 - Morning Routine
07:00 - Meditation
07:20 - Homework Block 1
09:20 - Extra Practice Block 1
10:50 - Break 1
11:10 - Extra Practice Block 2
12:40 - Lunch
13:10 - Homework Block 2
14:10 - Revision Block 1
14:40 - Extra Practice Block 3
15:40 - Homework Block 3
16:00 - Lectures
21:30 - Break 2 + Dinner
22:00 - Homework Block 4
22:40 - Revision Block 2
23:10 - Wind Down
23:30 - Sleep
