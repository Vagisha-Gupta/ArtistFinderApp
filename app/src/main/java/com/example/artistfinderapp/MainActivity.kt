package com.example.artistfinderapp

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.artistfinderapp.ApiService.ArtistService
import com.example.artistfinderapp.Database.ArtistDatabase
import com.example.artistfinderapp.Model.Artist
import com.example.artistfinderapp.Model.ResponseModel
import com.example.artistfinderapp.Repository.ArtistRepository
import com.example.artistfinderapp.ViewModel.ArtistViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URLEncoder

class MainActivity : AppCompatActivity(){








    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





        val editInput = findViewById<View>(R.id.input_artist) as EditText




         arrow_proceed.setOnClickListener{



            if(editInput.text.toString().isEmpty()){
                Toast.makeText(this,"Please enter name of Artist",Toast.LENGTH_SHORT).show()
            }
            else{

                var editText: String =  editInput.text.toString()
                val intent = Intent(this,ResultActivity::class.java)
                intent.putExtra(Constants.ARTIST_NAME,editText)
                startActivity(intent)


            }
        }
    }


}