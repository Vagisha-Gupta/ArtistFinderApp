package com.example.artistfinderapp.ApiService

import com.example.artistfinderapp.Model.Artist
import com.example.artistfinderapp.Model.ResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtistService {

    @GET("search")
    fun getResults(@Query("term") searchTerm:String): Call<ResponseModel>

}