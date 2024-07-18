package com.example.peliculasapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.peliculasapp.data.Movie
import com.example.peliculasapp.databinding.ItemMoviesBinding
import com.squareup.picasso.Picasso

class adapterMovie (
    private var dataSet: List<Movie> = emptyList(),
    private val onItemClickListener: (Int) -> Unit
): RecyclerView.Adapter<CharacteresViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacteresViewHolder {
        val binding =
            ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacteresViewHolder(binding)


    }


    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: CharacteresViewHolder, position: Int) {
        holder.render(dataSet[position])
        holder.itemView.setOnClickListener { onItemClickListener(position) }
    }

    fun updateData(dataSet: List<Movie>) {
        this.dataSet = dataSet
        notifyDataSetChanged()
    }


}






class CharacteresViewHolder(val binding: ItemMoviesBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun render(movie: Movie) {
        binding.nameTextView.text = movie.Title
        Picasso.get().load(movie.Poster).into(binding.photoImageView)
    }
    }