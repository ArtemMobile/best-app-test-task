package com.example.bestapptesttask.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.bestapptesttask.R
import com.example.bestapptesttask.data.Resource
import com.example.bestapptesttask.databinding.FragmentSplashBinding
import com.example.bestapptesttask.presentation.extensions.showErrorAlertDialog
import com.example.bestapptesttask.presentation.viewmodel.MovieViewModel
import com.example.bestapptesttask.utils.Constants.CANT_FETCH_DATA_ERROR_TITLE
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private val binding: FragmentSplashBinding by lazy {
        FragmentSplashBinding.inflate(layoutInflater)
    }

    private val movieViewModel by viewModels<MovieViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
    }

    private fun setObservers() {
        movieViewModel.dbMovies.observe(viewLifecycleOwner) {
            if (it.isEmpty()) movieViewModel.getMoviesFromRemote() else {
                lifecycleScope.launch {
                    delay(2000)
                    findNavController().navigate(R.id.action_splashFragment_to_filmsFragment)
                }
            }
        }

        movieViewModel.remoteMovies.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    requireContext().showErrorAlertDialog(
                        CANT_FETCH_DATA_ERROR_TITLE,
                        it.message.toString()
                    ) {
                        movieViewModel.getMoviesFromRemote()
                    }
                }

                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    lifecycleScope.launch {
                        it.data?.let {
                            movieViewModel.saveMoviesToLocalDatabase(it.docs)
                            delay(2500)
                            movieViewModel.getAllMoviesFromDatabase()
                        }
                    }
                }
            }
        }
    }
}