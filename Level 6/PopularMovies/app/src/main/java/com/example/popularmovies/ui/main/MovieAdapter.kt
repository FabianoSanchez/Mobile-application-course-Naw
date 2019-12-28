package com.example.popularmovies.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.popularmovies.R
import com.example.popularmovies.model.Movie
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter (private var movies: List<Movie>, private val onClick: (Movie) -> Unit):
        RecyclerView.Adapter<MovieAdapter.ViewHolder>() {


    private lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.movie_item,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(movies[position])
    }

    inner class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        init{
            itemView.setOnClickListener{onClick(movies[adapterPosition])}
        }

        fun bind(movie: Movie){
            var position = adapterPosition
            itemView.tvRanking.text = "${++position}."
            Glide.with(context).load(movie.getPosterUrl()).into(itemView.ivPoster)
        }
    }
}

