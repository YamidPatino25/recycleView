package com.example.recycleview.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class  User(val nombre:String,val email:String,val phone:String,val imagen:String, val humidity:String): Parcelable{}