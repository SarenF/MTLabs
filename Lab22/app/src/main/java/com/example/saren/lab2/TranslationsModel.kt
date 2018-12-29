package com.example.saren.lab2

import android.os.Parcel
import android.os.Parcelable
import androidx.versionedparcelable.ParcelField
import androidx.versionedparcelable.VersionedParcelize
import kotlinx.android.*
import java.util.*

@VersionedParcelize
data class TranslationsModel(val id: Long, val Russian: String, val English: String): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(Russian)
        parcel.writeString(English)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TranslationsModel> {
        override fun createFromParcel(parcel: Parcel): TranslationsModel {
            return TranslationsModel(parcel)
        }

        override fun newArray(size: Int): Array<TranslationsModel?> {
            return arrayOfNulls(size)
        }
    }

}

