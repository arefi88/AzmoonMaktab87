package com.example.azmoonmaktab87

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.azmoonmaktab87.databinding.FragmentMoviesDatabaseBinding
import com.example.azmoonmaktab87.repository.MovieDatabaseAdapter
import com.example.data.Movie
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MoviesToDatabaseFragment : Fragment() {
    private var _binding:FragmentMoviesDatabaseBinding?=null
    private val binding get() = _binding!!
    private val viewModel:MovieViewModel by viewModels()
    @Inject
    lateinit var movieAdapter: MovieDatabaseAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentMoviesDatabaseBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieAdapter=MovieDatabaseAdapter{movie->
            viewModel.insertMovie(movie)
        }
        viewModel.getMoviesDatabase()
        viewModel.movieListDatabase.observe(viewLifecycleOwner){
            movieAdapter.differ.submitList(it.toList())
        }
        binding.rvMovieDatabase.apply {
            layoutManager=LinearLayoutManager(requireContext())
            adapter=movieAdapter
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}