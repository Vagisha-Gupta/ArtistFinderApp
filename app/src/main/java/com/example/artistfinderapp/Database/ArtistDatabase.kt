package com.example.artistfinderapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.artistfinderapp.Dao.ArtistDao
import com.example.artistfinderapp.Model.Artist
import java.util.concurrent.ExecutorService


@Database(entities = [(Artist::class)], version = 4)
abstract class ArtistDatabase: RoomDatabase() {

    abstract fun artistDao(): ArtistDao




    companion object {

        @Volatile
        private var INSTANCE: ArtistDatabase? = null

        fun getDatabase(context: Context) :ArtistDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                        .databaseBuilder(context, ArtistDatabase::class.java, "ARTIST_DATABASE")
                        .fallbackToDestructiveMigration()
                        .build()

                return INSTANCE!!

            }
        }

    }


}