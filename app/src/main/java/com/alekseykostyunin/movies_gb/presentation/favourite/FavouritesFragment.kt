package com.alekseykostyunin.movies_gb.presentation.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.alekseykostyunin.movies_gb.R
import com.alekseykostyunin.movies_gb.databinding.FragmentFavouriteListBinding
import com.alekseykostyunin.movies_gb.domain.Movie

class FavouritesFragment : Fragment() {

    private var _binding: FragmentFavouriteListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouriteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: FavouriteMoviesViewModel by viewModels()

        val moviesAdapter = FavouriteMoviesAdapter(requireContext())
        binding.recyclerView.adapter = moviesAdapter
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        viewModel.getMovies().observe(viewLifecycleOwner) { moviesAdapter.movies = it }

        moviesAdapter.onMovieClickListener = object : FavouriteMoviesAdapter.OnMovieClickListener {
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