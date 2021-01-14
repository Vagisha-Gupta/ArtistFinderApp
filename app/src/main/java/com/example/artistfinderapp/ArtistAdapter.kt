package com.example.artistfinderapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.artistfinderapp.Model.Artist

class ArtistAdapter(var context:Context, var list:List<Artist>):RecyclerView.Adapter<ArtistAdapter.MyViewHolder>() {



    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val artWorkUrl100 :  ImageView =view.findViewById(R.id.iv_each_view)
        val trackName : TextView = view.findViewById(R.id.tv1_each_view)
        val collectionName: TextView =view.findViewById(R.id.tv2_each_view)
    }





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(context).inflate(R.layout.each_view,parent,false)
        return MyViewHolder(itemView)


    }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

            val singleAlbum = list[position]


            holder.trackName.text= singleAlbum.trackName
            holder.collectionName.text = singleAlbum.collectionName

            Glide.with(context).load(singleAlbum.artworkUrl100).into(holder.artWorkUrl100)




        }

        override fun getItemCount(): Int {
            return list.size
        }
    }
