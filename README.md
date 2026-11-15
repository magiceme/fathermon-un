# Fathérmon Un: Soul Clash v1.8

Offline Android app, package `com.fathermon.soulclash`, version `1.8` (18), Android 7.0/API 24 or newer.

The supplied `index.html` and all five launcher icon PNGs are unchanged. This preserves all 21 original embedded creature sprites, the 100-level Orsernae Journey, Chaos cards, animations, and existing localStorage save keys. The supplied words logo remains the app icon. The app requires no Internet permission.

## GitHub Actions APK

Upload this project's contents to the root of a GitHub repository, including `.github/workflows/android-debug.yml`. Pushes and pull requests run the build automatically. For a manual build, open **Actions → Build Fathermon Un v1.8 APK → Run workflow**.

The workflow installs Java 17, Gradle 8.9 and Android SDK 35, verifies original asset checksums, then runs `:app:assembleDebug :app:lintDebug`. Download **Fathermon-Un-Soul-Clash-v1.8-debug** from the successful run's **Artifacts** section. Extract the ZIP and install `Fathermon-Un-Soul-Clash-v1.8-debug.apk`. The artifact includes `SHA256SUMS.txt` and is retained for 90 days. Build reports are uploaded separately.

This is a signed debug APK for testing; no release signing secret is required. Allow installation from the app opening the APK when Android requests it.

## Local build

Install JDK 17 and Android SDK platform 35/build-tools 35.0.0. Set `ANDROID_HOME` or create an untracked `local.properties` with `sdk.dir=/path/to/android-sdk`. Use the bundled Gradle 8.9 wrapper:

```sh
python3 tools/verify_assets.py
./gradlew :app:assembleDebug :app:lintDebug
```

The APK is generated at `app/build/outputs/apk/debug/app-debug.apk`. Android Studio can also open this directory and sync the project.

## Offline saves and updates

Profile, statistics and Journey progress use WebView localStorage. The original `file:///android_asset/index.html` origin, package ID and save keys are retained. Browser saves are separate from Android app saves. Uninstalling the app or clearing its storage deletes local progress.

To update an already installed APK without removing its saves, use the same signing key and package ID. Fresh GitHub runners normally generate a different debug key; APKs from different runs may require uninstalling the previous build. Keep and reuse a signing key before relying on debug APKs for long-term progress. In-progress matches follow the original game's save behavior; this build does not add match-resume functionality.
