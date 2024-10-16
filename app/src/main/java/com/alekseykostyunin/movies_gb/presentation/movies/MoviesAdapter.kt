package com.alekseykostyunin.movies_gb.presentation.movies

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alekseykostyunin.movies_gb.R
import com.alekseykostyunin.movies_gb.databinding.MovieItemBinding
import com.alekseykostyunin.movies_gb.domain.movies.Movie
import com.bumptech.glide.Glide

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    var onMovieClickListener: OnMovieClickListener? = null

    var movies: List<Movie> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var binding: MovieItemBinding = MovieItemBinding.bind(itemView)
        var imageViewPoster = binding.imageViewPoster
        var textViewRating = binding.textViewRating
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.movie_item,
                parent,
                false
            )
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        Log.d("TEST_Position", position.toString())
        val movie = movies[position]
        Glide.with(holder.itemView)
            .load(movie.poster.url)
            .into(holder.imageViewPoster)

        val rating: Double? = movie.rating.kp
        val backgroundId = if (rating!! >= 7.0) {
            R.drawable.circle_green
        } else if (rating > 5.0) {
            R.drawable.circle_orange
        } else {
            R.drawable.circle_red
        }

        holder.textViewRating.text = rating.toString().substring(0, 3)
        holder.textViewRating.setBackgroundResource(backgroundId)

        holder.itemView.setOnClickListener {
            if (onMovieClickListener != null) {
                onMovieClickListener?.onMovieClick(movie)
            }
        }
    }

    interface OnMovieClickListener {
        fun onMovieClick(movie: Movie)
    }
}

