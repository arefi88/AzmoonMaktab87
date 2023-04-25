package com.example.azmoonmaktab87.repository

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.azmoonmaktab87.databinding.ItemDatabaseMovieBinding
import com.example.azmoonmaktab87.databinding.ItemMovieBinding
import com.example.data.Movie
import com.example.data.Results
import javax.inject.Inject

class MovieDatabaseAdapter @Inject constructor(private val onItemClicked:(Movie)->Unit): RecyclerView.Adapter<MovieDatabaseAdapter.ViewHolder>() {
    private lateinit var binding: ItemDatabaseMovieBinding
    inner class ViewHolder: RecyclerView.ViewHolder(binding.root){
        fun setData(movie:Movie){

            binding.txtTitle.text=movie.title

            binding.imgMovie.load(movie.posterPath){
                crossfade(true)
                transformations(CircleCropTransformation())
            }

              binding.btnSave.setOnClickListener {
                  onItemClicked(movie)
              }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding= ItemDatabaseMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
        holder.setIsRecyclable(true)



    }

    override fun getItemCount() = differ.currentList.size


    private val differCallback= object : DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id==newItem.id
        }

    }
    val differ= AsyncListDiffer(this,differCallback)
}