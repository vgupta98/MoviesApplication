package com.rentmanager.moviesapplication.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.rentmanager.moviesapplication.getInjector
import com.rentmanager.moviesapplication.viewmodels.MoviesViewModel
import kotlinx.coroutines.launch

class MoviesListFragment: Fragment() {
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
////        val moviesViewModel: MoviesViewModel by viewModels<> {  }
//        val moviesViewModel = ViewModelProvider(this, getInjector(requireContext()).provideMovieViewModelFactory()).get(MoviesViewModel::class.java)
//
//        lifecycleScope.launch {
//            moviesViewModel.getPopularMovies("2")
//            Log.d("MoviesListFragment", moviesViewModel.moviesResponse.toString())
//        }
//
//        return ComposeView(requireContext()).apply {
//            setContent {
//                MoviesListUI()
//            }
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val moviesViewModel = ViewModelProvider(this, getInjector(requireContext()).provideMovieViewModelFactory()).get(MoviesViewModel::class.java)

        lifecycleScope.launch {
            moviesViewModel.getPopularMovies("2")
            Log.d("MoviesListFragment", moviesViewModel.moviesResponse.value.toString())
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }
}