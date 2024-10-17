package com.alekseykostyunin.movies_gb.presentation.detail

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alekseykostyunin.movies_gb.R
import com.alekseykostyunin.movies_gb.databinding.ReviewItemBinding
import com.alekseykostyunin.movies_gb.domain.Review

class ReviewsAdapter(context: Context) : RecyclerView.Adapter<ReviewsAdapter.ReviewsViewHolder>(){

    var reviews: List<Review> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    inner class ReviewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private var binding : ReviewItemBinding = ReviewItemBinding.bind(itemView)
        var linearLayoutContainer = binding.linearLayoutContainer
        var textViewAuthor = binding.textViewAuthor
        var textViewReview = binding.textViewReview

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.review_item,
                parent,
                false
            )
        return ReviewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return reviews.size
    }

    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
        Log.d("TEST_Position_R",position.toString())
        val review = reviews[position]
        holder.textViewAuthor.text = review.author
        holder.textViewReview.text = review.review

        var type = review.type
        val colorResId = when(review.type){
            "Позитивный" -> android.R.color.holo_green_light
            "Нейтральный" -> android.R.color.holo_orange_light
            else -> android.R.color.holo_red_light
        }
        holder.linearLayoutContainer.setBackgroundResource(colorResId)


    }
}