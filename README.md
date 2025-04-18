# MusEnergy
Welcome to Android "MusEnergy", which is currently at version 0.0.1  :)  

This is my personal Demo project which is my Sandbox. Here I experiment with Android development technologies, 
and also demonstrate my skills and approaches in programming.

This project is about music. For now, It collects various articles about music on the internet and shows articles. 
You can also search news by category and genre.

### NOTE: The project is currently under development and is about 33% complete!


## Project description 
### API used: [News API](https://newsapi.org/)
This is a free API, that allows you to do 100 free requests per day. 
In this application, I sort the articles in the request by key music tags in @Query parameters.


### Current state: 
At the moment, the main screen with various articles on the topic of music, as well as the category search screen, has been implemented.
There is also a screen "Article Details".
On the details screen, it is possible to share the article, add to favorites or go to the original site.

### Future: 
It is planned to implement something related with video-content. The player is already in the project,
but so far I have no ideas how to use it :D There's deprecated hardcoded Storied feature which I disabled for now. 

Also I'm going to add "profile" and "settings" screens, and implement manual searching.

### Technologies: 
The application is developed in accordance with the best practices recommended in Android development.

It's based on [Clean Architecture](https://medium.com/android-dev-hacks/detailed-guide-on-android-clean-architecture-9eab262a9011).
Main technologies and libraries: 
- Hilt
- Android Navigation Component 
- Kotlin Coroutines 
- Retrofit 2 
- OkHttp
- DataBinding
- Jetpack Compose (for new screens)
- Coil 3
- Media 3 (ex. ExoPlayer 2)


# If you want to install the application via AS, or if you also want to contribute: 

## Requirements
- Android Studio "Ladybug Feature Drop" | 2024.2.2 or later
- JDK version 19
- Kotlin version 2.1.10
- Gradle version 8.14 
- Android SDK version 35

Android Studio and all required tools can be downloaded here: 
`https://developer.android.com/studio`

## Installation 
##### To run the project, please follow the instructions below: 

- Clone this repository to your local machine using git clone `https://github.com/username/project-name.git`
- Open the project in Android Studio.
- Connect your Android device or run AVD via Device Manager 
- Build the project (⌘ + F9). 
- Run the project (⌃ + R)

## Code style 

##### Kotlin
- In this project I follow [general Android code-style guide](https://source.android.com/docs/core/architecture/hidl/code-style)
and [Official Kotlin coding conventions](https://kotlinlang.org/docs/coding-conventions.html).
The code style is enforced using [Spotless](https://github.com/diffplug/spotless) and [Ktlint](https://github.com/pinterest/ktlint) plugins.
- The only language is 100% Kotlin. 
- Please try to not leave extra commented out lines, logs, hard code, etc., which can make the code visually difficult to read.

##### Git 

* Don’t commit big files unless they absolutely require git. Even in this case, prefer storing all big files in a separate submodule. That’s because git history can become very big and it will be pain for others to use the repo.
- Structure your commit message like this:
```
One line summary (less than 50 characters) 
Longer description (wrap at 72 characters)
```
* Commit summary:
  * Less than 50 characters
  * What was changed
  * Imperative present tense (fix, add, change): ("Fix bug 123.", "Add 'foobar' command.", "Change default timeout to 123."). Please try to do not use past tense in the message.

* Commit description:  
  * Wrap at 72 characters
  * Why, explain intention and implementation approach
  * Use Present tense 

- Commit atomicity:
  * Break up logical changes
  * Make whitespace changes separately

- Branch naming:
  * Use hyphens as word separator
  * Use namespaces and ticket name 
  * Use markers for the branches:
    * `feature` - a branch with implementation of the new feature 
    * `bugfix` - a branch with bugfixes
    * `hotfix` - a branch with hotfixes
    * `release` - release branch 

## Contributing 
##### To contribute, please follow the instructions below: 
- Install the project according to the instructions above
- Create a new branch (`git checkout -b feature/<ticket-name>/your-feature-name`)
- Make your changes 
- Commit your changes (`git commit -am 'Feature description'`)
- Push to the branch (`git push origin feature/<ticket-name>/your-feature-name`)
- Create a new Pull Request

## Versioning 
#### Current version: 0.0.1
This project follows the [Semantic Versioning](https://semver.org/) standard. The versioning format is as follows:
```
X.Y.Z
```
- X: Major version (incompatible changes)
- Y: Minor version (new features, backwards-compatible changes)
- Z: Patch version (bug fixes, backwards-compatible changes)
