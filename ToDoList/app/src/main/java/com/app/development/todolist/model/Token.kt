package com.app.development.todolist.model

import android.os.Parcelable
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


/** Data class for Token. Used for receiving the token from GoogleApi*/
@Parcelize
data class Token(
    @SerializedName("access_token") var accessToken: String,
    @SerializedName("id_token") var idToken: String,
    @SerializedName("refresh_token") var refreshToken: String,
    @SerializedName("expires_in") var expiredIn: Int,
    @SerializedName("token_type") var tokenType: String,
    var email: String,
    @PrimaryKey var id: Long? = null
) : Parcelable