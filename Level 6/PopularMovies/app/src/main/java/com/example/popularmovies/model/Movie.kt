package com.example.popularmovies.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @SerializedName("poster_path") var posterPath: String,
    @SerializedName("backdrop_path") var backdropPath: String,
    @SerializedName("title") var title: String,
    @SerializedName("release_date") var release: String,
    @SerializedName("overview") var overview: String,
    @SerializedName("vote_average") var rating:Double
): Parcelable {
    fun getPosterUrl() = "https://image.tmdb.org/t/p/w500$posterPath"
    fun getBackgroundUrl() = "https://image.tmdb.org/t/p/w500$backdropPath"
}