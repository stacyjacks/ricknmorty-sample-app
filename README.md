# Rick and Morty characters

This is a sample Android app based on rickandmortyapi.com. It's been created using: 
* Kotlin
* MVVM (Model-View-ViewModel) design pattern
* Paging 3 library
* LiveData
* Navigation Component
* Data binding
* Kotlin Coroutines
* Rick and Morty REST API

## Project description

This project displays an infinite list of characters from the Rick and Morty TV show, which are loaded onto a ``RecyclerView``, with an infinite scroll implemented through the Paging 3 library for a more comfortable browsing experience.

When the list of characters is displayed, the user can navigate to a detail ``fragment`` of any individual character they choose, and this screen will display some additional information about each character, including their __status__ in the TV show (dead, alive or unknown), __gender__, __last known location__, __origin__... This detail screen has been implemented using two-way data binding.

Given the simplicity of this app, I've decided to use a shared ``view model`` for both fragments, because the detail screen basically contains the same data, only with some additional details about each character.

This sample app implements Koin for the purpose of ``Dependency Injection`` and ``testing``. Some basic tests are provided in their respective folders.

The project targets API 30, with a min SDK 24.

## List of third-party libraries used in this project:
* [Retrofit 2](https://github.com/square/retrofit)
* [Moshi](https://github.com/square/moshi)
* [Glide](https://github.com/bumptech/glide)
* [Material components](https://github.com/material-components/material-components-android)
* [Stetho](https://github.com/facebookarchive/stetho)
* [Koin](https://insert-koin.io/)
* [Mockito](https://site.mockito.org/)

## License
[GNU General Public License v3](https://www.gnu.org/licenses/gpl-3.0.en.html)

Data provided by https://rickandmortyapi.com/.