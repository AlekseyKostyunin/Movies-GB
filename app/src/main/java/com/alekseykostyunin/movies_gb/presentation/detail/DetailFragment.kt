package com.alekseykostyunin.movies_gb.presentation.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alekseykostyunin.domain.Movie
import com.alekseykostyunin.movies_gb.databinding.FragmentMovieDetailBinding
import com.bumptech.glide.Glide

class DetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    private val movieArg by lazy {
        arguments?.getSerializable("movie") as Movie
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerViewTrailers = binding.recyclerViewTrailers
        val recyclerViewReviews = binding.recyclerViewReviews
        val trailersAdapter = TrailersAdapter(requireContext())
        recyclerViewTrailers.adapter = trailersAdapter
        val reviewsAdapter = ReviewsAdapter(requireContext())
        recyclerViewReviews.adapter = reviewsAdapter
        val viewModel = ViewModelProvider(this)[MovieDetailViewModel::class.java]
        val movie = movieArg
        Glide.with(this)
            .load(movie.poster.url)
            .into(binding.imageViewPoster)
        binding.textViewTitle.text = movie.name
        binding.textViewYear.text = movie.year.toString()
        binding.textViewDescription.text = movie.description

        movie.id.let { viewModel.loadTrailers(it) }
        viewModel.getTrailers().observe(viewLifecycleOwner) {
            Log.d("TEST_MovieDetailActivity", it.toString())
            trailersAdapter.trailers = it
        }
        trailersAdapter.onTrailerClickListener = object : TrailersAdapter.OnTrailerClickListener {
            override fun onTrailerClick(trailer: com.alekseykostyunin.domain.Trailer) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(trailer.url)
                startActivity(intent)
            }
        }

        movie.id.let { viewModel.loadReviews(it) }
        viewModel.getReviews().observe(viewLifecycleOwner) {
            reviewsAdapter.reviews = it
        }

        val starOff = ContextCompat.getDrawable(
            requireContext(),
            android.R.drawable.star_big_off
        )
        val starOn = ContextCompat.getDrawable(
            requireContext(),
            android.R.drawable.star_big_on
        )

        movie.id.let { movieId ->
            viewModel.getFavouriteMovie(movieId)
                .observe(viewLifecycleOwner) {
                    if (it == null) {
                        binding.imageViewStar.setImageDrawable(starOff)
                        binding.imageViewStar.setOnClickListener {
                            viewModel.insertMovie(movie)
                        }
                    } else {
                        binding.imageViewStar.setImageDrawable(starOn)
                        binding.imageViewStar.setOnClickListener {
                            viewModel.removeMovie(movie.id)
                        }
                    }
                }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}