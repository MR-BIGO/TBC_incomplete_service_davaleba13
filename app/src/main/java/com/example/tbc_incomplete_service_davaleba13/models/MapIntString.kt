package com.example.tbc_incomplete_service_davaleba13.models

import android.os.Parcel
import android.os.Parcelable

data class MapIntString(val map: Map<Int, String>) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readHashMap(Int::class.java.classLoader) as Map<Int, String>)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeMap(map)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MapIntString> {
        override fun createFromParcel(parcel: Parcel): MapIntString {
            return MapIntString(parcel)
        }

        override fun newArray(size: Int): Array<MapIntString?> {
            return arrayOfNulls(size)
        }
    }
}
