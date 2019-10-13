package com.example.rockpaperscissors.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Statistics(
    var wins:Int,
    var losses:Int,
    var draws:Int
):Parcelable