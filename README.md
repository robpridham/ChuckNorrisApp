# Chuck Norris App

This is a demonstration app in response to the 'Internet Chuck Norris Database API' brief.

It's written entirely in Kotlin and built using Android Studio 3.2.1.

It attempts to demonstrate some key architectural principles such as:

* Kotlin idioms
* separation for testability
* single responsibility, dependency injection etc.
* fragment-oriented design incorporating viewmodel, controller and view wrapper 
* fragment lifecycle
* typical JSON & REST API interaction

as well as basic Android design fundamentals.

There's limited unit tests powered by Mockito.

As of close of play on 12/11/18, only the first feature - 'Random Joke' - is operable. More will be added over time, availability permitting.

Major TODOs to incorporate remaining features are:

* Non-dialog fragment transitions
* Text input and validation in support of 'text input' feature
* RecyclerView & binding, potentially with `PagedList`, in support of never-ending list feature
