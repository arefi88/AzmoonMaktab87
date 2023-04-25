package com.example.azmoonmaktab87

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.azmoonmaktab87.databinding.FragmentMoviesBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MoviesFragment : Fragment() {
    private var _binding:FragmentMoviesBinding?=null
    private val binding get() = _binding!!
    @Inject
    lateinit var moviesAdapter:MoviesAdapter
    private val viewModel:MovieViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentMoviesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovies()
        viewModel.moviesLiveData.observe(viewLifecycleOwner){
            moviesAdapter.differ.submitList(it.results?.toList())
        }


        binding.rvMovie.apply {
            layoutManager=LinearLayoutManager(requireContext())
            adapter=moviesAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}