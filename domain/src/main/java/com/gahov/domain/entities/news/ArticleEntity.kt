package com.gahov.domain.entities.news

import android.os.Parcel
import android.os.Parcelable

data class ArticleEntity(
    val author: String? = null,
    val title: String? = null,
    val description: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val publishedAt: String? = null,
    val content: String? = null,
    val sourceId: String? = null,
    val sourceName: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(author)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(url)
        parcel.writeString(urlToImage)
        parcel.writeString(publishedAt)
        parcel.writeString(content)
        parcel.writeString(sourceId)
        parcel.writeString(sourceName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ArticleEntity> {
        override fun createFromParcel(parcel: Parcel): ArticleEntity {
            return ArticleEntity(parcel)
        }

        override fun newArray(size: Int): Array<ArticleEntity?> {
            return arrayOfNulls(size)
        }
    }
}