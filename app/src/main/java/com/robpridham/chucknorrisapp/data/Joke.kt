package com.robpridham.chucknorrisapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Joke(val id: Int, val joke: String): Parcelable

data class SingleJoke(val type: String, val value: Joke)

//data class MultipleJokes(val type: String, val value: List<Joke>)