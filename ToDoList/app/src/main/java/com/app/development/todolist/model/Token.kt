package com.app.development.todolist.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Token(
    @SerializedName("access_token") var accessToken: String,
    @SerializedName("id_token") var idToken:String,
    @SerializedName("refresh_token") var refreshToken: String,
    @SerializedName("expires_in") var expiredIn: Int,
    @SerializedName("token_type") var tokenType:String,
    @PrimaryKey var id:Long? = null
): Parcelable