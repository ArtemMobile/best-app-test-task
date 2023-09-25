package com.example.bestapptesttask.presentation.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.bestapptesttask.R
import com.example.bestapptesttask.data.Resource
import com.example.bestapptesttask.databinding.FragmentFilmBinding
import com.example.bestapptesttask.domain.model.Movie
import com.example.bestapptesttask.presentation.adapter.ActorAdapter
import com.example.bestapptesttask.presentation.extensions.showErrorAlertDialog
import com.example.bestapptesttask.presentation.viewmodel.MovieViewModel
import com.example.bestapptesttask.utils.Constants.CANT_FETCH_DATA_ERROR_TITLE
import com.example.bestapptesttask.utils.Constants.MOVIE_ID
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FilmFragment : Fragment() {

    private val binding: FragmentFilmBinding by lazy {
        FragmentFilmBinding.inflate(layoutInflater)
    }

    private val movieViewModel by viewModels<MovieViewModel>()
    private var movieId: Int? = null
    private var movieIsBookmarked: Boolean? = null
    private var movie: Movie? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieId = arguments?.getInt(MOVIE_ID)
        lifecycleScope.launch {
            movieId?.let {
                movieViewModel.getMovieById(it)
                movieViewModel.getMovieBookmarked(it)
            }
        }

        setObservers()
        applyClicks()
    }

    private fun setObservers() {
        movieViewModel.movieById.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    hideProgressbar()
                    requireContext().showErrorAlertDialog(
                        CANT_FETCH_DATA_ERROR_TITLE,
                        it.message.toString()
                    ) {
                        showProgressbar()
                        movieId?.let { id -> movieViewModel.getMovieById(id) }
                    }
                }

                is Resource.Loading -> {
                    showProgressbar()
                }

                is Resource.Success -> {
                    hideProgressbar()
                    it.data?.let {
                        movie = it
                        bindMovieData(it)
                    }
                }
            }
        }
        movieViewModel.movieIsBookmarked.observe(viewLifecycleOwner) {
            binding.apply {
                if (it) {
                    imageViewBookmark.setImageResource(R.drawable.baseline_bookmark_remove_24)
                } else {
                    imageViewBookmark.setImageResource(R.drawable.baseline_bookmark_add_24)
                }
            }
            movieIsBookmarked = it
        }
    }

    private fun bindMovieData(movie: Movie) {
        binding.apply {
            textView.visibility = View.VISIBLE
            imageViewBookmark.visibility = View.VISIBLE
            imageViewStar.visibility = View.VISIBLE
            textViewTitle.text = movie.title
            textViewPlot.text = movie.plot
            textViewRelease.text = movie.releaseDate.toString()
            textViewRating.text = movie.rating.toString()
            movie.trailer?.let {
                buttonTrailer.visibility = View.VISIBLE
            }

            Glide.with(requireContext())
                .load(movie.image)
                .into(imageViewPreview)

            binding.recyclerViewActors.apply {
                adapter = movie.actorList?.let { ActorAdapter(requireContext(), it) }
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }

    private fun applyClicks() {
        binding.apply {
            bookMarkField.setOnClickListener {
                movieIsBookmarked?.let { bookmarked ->
                    movie?.let { movie ->
                        onBookmarkClicked(
                            movie,
                            bookmarked
                        )
                    }
                }
                movieId?.let { id -> movieViewModel.getMovieBookmarked(id) }
            }
            buttonTrailer.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(movie?.trailer)
                startActivity(intent)
            }
        }
    }

    private fun showProgressbar() {
        binding.progressbar.visibility = View.VISIBLE
    }

    private fun hideProgressbar() {
        binding.progressbar.visibility = View.GONE
    }

    private fun onBookmarkClicked(movie: Movie, bookmarked: Boolean) {
        movieViewModel.bookMarkMovie(movie, !bookmarked)
    }
}