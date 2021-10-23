package com.example.note.Models

import android.os.Parcel
import android.os.Parcelable

class NoteDetailsData(var id:Int,var title: String?, var descption: String?) : Parcelable {


    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, p1: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(descption)
    }

    companion object CREATOR : Parcelable.Creator<NoteDetailsData> {
        override fun createFromParcel(parcel: Parcel): NoteDetailsData {
            return NoteDetailsData(parcel)
        }

        override fun newArray(size: Int): Array<NoteDetailsData?> {
            return arrayOfNulls(size)
        }
    }


}