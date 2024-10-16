package com.alekseykostyunin.movies_gb.presentation.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.alekseykostyunin.movies_gb.R
import com.alekseykostyunin.movies_gb.databinding.FragmentMovieListBinding
import com.alekseykostyunin.movies_gb.domain.movies.Movie

class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val moviesAdapter = MoviesAdapter()
        binding.recyclerView.adapter = moviesAdapter
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        val viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        viewModel.getMovies().observe(viewLifecycleOwner) { movies ->
            moviesAdapter.movies = movies
        }
        viewModel.loadMovies()
        viewModel.getIsLoading().observe(viewLifecycleOwner) {
            if (it) {
                binding.progress.visibility = View.VISIBLE
            } else {
                binding.progress.visibility = View.GONE
            }
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refresh()
        }

        moviesAdapter.onMovieClickListener = object : MoviesAdapter.OnMovieClickListener {
            override fun onMovieClick(movie: Movie) {
                requireActivity().findNavController(R.id.nav_host_activity_main)
                    .navigate(
                        resId = R.id.action_main_to_details,
                        args = bundleOf("movie" to movie),
                    )
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}