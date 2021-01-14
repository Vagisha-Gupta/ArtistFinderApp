package com.example.artistfinderapp.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.artistfinderapp.Model.Artist

@Dao
interface ArtistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArtists(artistList:List<Artist>)


    @Query("SELECT * FROM Artists WHERE artistName LIKE :name")
    fun getArtists(name:String):LiveData<List<Artist>>

    @Query("DELETE FROM Artists")
    fun deleteArtists()


}