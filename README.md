# Chuck Norris App

This is a demonstration app in response to the 'Internet Chuck Norris Database API' brief.

It's written entirely in Kotlin and built using Android Studio 3.2.1.

It attempts to demonstrate some key architectural principles such as:

* Kotlin idioms
* separation for testability
* single responsibility, dependency injection etc.
* fragment-oriented design incorporating viewmodel, controller and view wrapper 
* fragment lifecycle and inter-fragment transitions
* typical JSON & REST API interaction

as well as basic Android design fundamentals.

There's limited unit tests powered by Mockito.

As of close of play on 13/11/18, only the first two features - 'Random Joke' and 'Text Input' - are implemented. If time permits, the third will be attempted. 

Major TODOs to incorporate the remaining feature are:

* Extension of the existing patterns to accommodate the third UI
* RecyclerView and adapter, potentially with `PagedList`, in support of never-ending list feature
* Further polish and review
