package com.example.artistfinderapp

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.artistfinderapp.ApiService.ArtistService
import com.example.artistfinderapp.Model.Artist
import com.example.artistfinderapp.Model.ResponseModel

import com.example.artistfinderapp.ViewModel.ArtistViewModel
import kotlinx.android.synthetic.main.activity_result.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URLEncoder

class ResultActivity : AppCompatActivity(){


   private var mProgressDialog:Dialog? = null

    lateinit var artistRecyclerView:RecyclerView

    lateinit var artistViewModel: ArtistViewModel



    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)


        val getIntent = intent.getStringExtra(Constants.ARTIST_NAME)
        var encodedString= URLEncoder.encode(getIntent,"UTF-8")
        artistViewModel = ViewModelProviders.of(this).get(ArtistViewModel::class.java)



        artistRecyclerView = findViewById(R.id.rv_artist)







              if(isNetworkAvailable(this)){
                  getApiData(encodedString)

              }
             artistViewModel.getArtist(this,getIntent!!)!!.observe(this, Observer {
                 setUpUi(it)
             })
        }


















    fun setUpUi(artist:List<Artist>){
        if(artist.isEmpty()){
            rv_artist.visibility= View.GONE
            ll_empty_view.visibility=View.VISIBLE

        }
        else{
            ll_empty_view.visibility=View.GONE
            rv_artist.visibility=View.VISIBLE
            populateRecyclerView(artist)
        }
    }


    fun populateRecyclerView(artistList:List<Artist>){


        var artistAdapter =ArtistAdapter(this,artistList)
        artistRecyclerView.layoutManager=GridLayoutManager(this,2)
        artistRecyclerView.adapter= artistAdapter
    }





    private fun isNetworkAvailable(context: Context):Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network      = connectivityManager.activeNetwork ?: return false
            val activeNetWork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                activeNetWork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetWork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetWork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {

            val networkInfo =connectivityManager.activeNetworkInfo

            return networkInfo!=null && networkInfo.isConnectedOrConnecting
        }
    }


    fun getApiData(string:String){


        val retrofit: Retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(
                GsonConverterFactory.create()).build()




        val artistService: ArtistService = retrofit.create(ArtistService::class.java)



        val artistResultsCallback: Call<ResponseModel> = artistService.getResults(string)

       showProgress()

        artistResultsCallback.enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                if(response.isSuccessful) {
                    hideProgress()
                    var artistResponse = response.body()!!.getArtistModel()!!
                       artistViewModel.insertArtist(this@ResultActivity,artistResponse)


                }
                else{
                     hideProgress()
                    Log.e("ResultActivity", "Error found")
                    return
                }

            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                hideProgress()

                Log.e("MainActivity","An error occurred")

            }


        })











    }


 fun showProgress(){
     mProgressDialog = Dialog(this)
     mProgressDialog!!.setContentView(R.layout.progress_bar)
     mProgressDialog!!.show()
 }
fun hideProgress(){
    if(mProgressDialog !=null){
        mProgressDialog!!.dismiss()
    }
}

}