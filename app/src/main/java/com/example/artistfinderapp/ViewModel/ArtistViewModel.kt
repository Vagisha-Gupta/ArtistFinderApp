package com.example.artistfinderapp.ViewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.example.artistfinderapp.Model.Artist
import com.example.artistfinderapp.Repository.ArtistRepository

class ArtistViewModel(): ViewModel() {

      var artistList:LiveData<List<Artist>>? = null

     fun insertArtist(context: Context, artist:List<Artist>){
         ArtistRepository.insertArtist(context,artist)
     }


    fun getArtist(context: Context, name: String):LiveData<List<Artist>>?{
        artistList = ArtistRepository.getArtist(context,name)
        return artistList
    }


}