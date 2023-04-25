package com.example.azmoonmaktab87

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.azmoonmaktab87.databinding.ItemMovieBinding
import com.example.data.Movies

import com.example.data.Results
import javax.inject.Inject

class MoviesAdapter @Inject constructor(): RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    private lateinit var binding:ItemMovieBinding
    inner class ViewHolder: RecyclerView.ViewHolder(binding.root){
        fun setData(result:Results){

            binding.txtTitle.text=result.title

            binding.imgMovie.load(result.poster_path){
                crossfade(true)
                transformations(CircleCropTransformation())
            }


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding=ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
        holder.setIsRecyclable(true)



    }

    override fun getItemCount() = differ.currentList.size


    private val differCallback= object : DiffUtil.ItemCallback<Results>(){
        override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem==newItem
        }

    }
    val differ= AsyncListDiffer(this,differCallback)
}