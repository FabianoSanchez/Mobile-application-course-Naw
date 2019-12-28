package com.example.popularmovies.model

import com.google.gson.annotations.SerializedName

data class Results(
    @SerializedName("results") var results: List<Movie>
)