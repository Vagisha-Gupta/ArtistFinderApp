package com.example.artistfinderapp.Model

import com.google.gson.annotations.SerializedName

class ResponseModel {

    @SerializedName("resultCount")
    var resultCount : Int = 0

    @SerializedName("results")
    var results: List<Artist>?= null



    fun geCount(): Int {
        return resultCount
    }

    fun setCount(resultCount: Int) {
        this.resultCount = resultCount
    }

    fun getArtistModel(): List<Artist>? {
        return results
    }

    fun setArtistModel(modelClass: List<Artist>?) {
        results = modelClass
    }


}