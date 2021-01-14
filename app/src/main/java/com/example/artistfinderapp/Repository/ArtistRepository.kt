package com.example.artistfinderapp.Repository


import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import com.example.artistfinderapp.Dao.ArtistDao
import com.example.artistfinderapp.Database.ArtistDatabase
import com.example.artistfinderapp.Model.Artist
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


class ArtistRepository {


    companion object {

        var artistDatabase: ArtistDatabase? = null

        var artistModel: LiveData<List<Artist>>? = null

        fun getDatabase(context: Context) : ArtistDatabase? {
            return ArtistDatabase.getDatabase(context)
        }

        fun insertArtist(context: Context, artist:List<Artist>) {

            artistDatabase = getDatabase(context)

            CoroutineScope(IO).launch {

                artistDatabase!!.artistDao().deleteArtists()
                artistDatabase!!.artistDao().insertArtists(artist)
            }

        }

        fun getArtist(context: Context, name:String) : LiveData<List<Artist>>? {

            artistDatabase = getDatabase(context)
            artistModel = artistDatabase!!.artistDao().getArtists(name)

            return  artistModel


        }

    }
    }











