package com.example.artistfinderapp.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Artists")
data class Artist (
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,

    @SerializedName("artistId")
     var artistId: Long,

    @SerializedName("artistName")
     var artistName: String,

    @SerializedName("collectionName")
    var  collectionName:String,

    @SerializedName("trackName")
    var trackName: String,

    @SerializedName("previewUrl")
    var previewUrl:String,

    @SerializedName("artworkUrl100")
    var artworkUrl100:String,

    )