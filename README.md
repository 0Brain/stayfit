# Stay fit 📺 (work-in-progress 👷🔧️👷‍♀️⛏)

## Android development

Stay fit  is an app which attempts to use the latest cutting edge libraries and tools. As a summary:

 * Entirely written in [Kotlin](https://kotlinlang.org/)
 * Uses [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines/coroutines-guide.html) throughout.
 * Uses many of the [Architecture Components](https://developer.android.com/topic/libraries/architecture/): Room Lifecycle, Navigation
 * Uses [Hilt](https://dagger.dev/hilt/) for dependency injection
 
### Code style
 
 This project uses [ktlint](https://github.com/pinterest/ktlint), provided via
 the [spotless](https://github.com/diffplug/spotless) gradle plugin, and the bundled project IntelliJ codestyle.
 
 If you find that one of your pull reviews does not pass the CI server check due to a code style conflict, you can
 easily fix it by running: `./gradlew spotlessApply`, or running IntelliJ/Android Studio's code formatter.

