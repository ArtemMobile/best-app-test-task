package com.example.bestapptesttask.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView.SmoothScroller
import com.example.bestapptesttask.R
import com.example.bestapptesttask.domain.model.MovieDb
import com.example.bestapptesttask.databinding.FragmentFilmsBinding
import com.example.bestapptesttask.presentation.adapter.MovieAdapter
import com.example.bestapptesttask.presentation.viewmodel.MovieViewModel
import com.example.bestapptesttask.utils.Constants.ALPHABET
import com.example.bestapptesttask.utils.Constants.MOVIE_ID
import com.example.bestapptesttask.utils.Constants.RATES
import com.example.bestapptesttask.utils.Constants.RATING
import com.example.bestapptesttask.utils.RxSearch
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers


@AndroidEntryPoint
class FilmsFragment : Fragment() {

    private val binding: FragmentFilmsBinding by lazy {
        FragmentFilmsBinding.inflate(layoutInflater)
    }

    private val movieViewModel by viewModels<MovieViewModel>()

    private lateinit var movieAdapter: MovieAdapter

    private lateinit var smoothScroller: SmoothScroller

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
        applyMoviesRecyclerView()
        applyClicks()
        applySearchView()
        applyFilterChips()
        initSmoothScroller()
    }

    private fun initSmoothScroller() {
        smoothScroller = object : LinearSmoothScroller(requireContext()) {
            override fun getVerticalSnapPreference(): Int {
                return SNAP_TO_START
            }

            override fun setTargetPosition(targetPosition: Int) {
                super.setTargetPosition(0)
            }
        }
    }

    private fun setObservers() {
        movieViewModel.dbMovies.observe(viewLifecycleOwner) {
            movieAdapter.updateMovies(it)
        }
    }

    private fun applyClicks() {
        binding.apply {
            bookMarkCard.setOnClickListener {
                findNavController().navigate(R.id.action_filmsFragment_to_bookmarksFragment)
            }
            editTextSearch.setOnCloseListener {
                editTextSearch.clearFocus()
                true
            }
        }
    }

    private fun applySearchView() {
        val disposable = RxSearch.fromView(binding.editTextSearch)
            .debounce(500, java.util.concurrent.TimeUnit.MILLISECONDS)
            .map { text -> text.lowercase().trim() }
            .distinctUntilChanged()
            .switchMap { s -> Observable.just(s) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { query ->
                filterListByText(query)
            }
    }

    private fun applyFilterChips() {
        binding.chipGroup.setOnCheckedStateChangeListener { _, checkedIds ->

            if (checkedIds.isNotEmpty()) {
                when (checkedIds.first()) {
                    R.id.chipAlphabet -> {
                        movieViewModel.getMoviesByParam(ALPHABET)
                    }

                    R.id.chipRates -> {
                        movieViewModel.getMoviesByParam(RATES)
                    }

                    R.id.chipRating -> {
                        movieViewModel.getMoviesByParam(RATING)
                    }
                }
            } else {
                movieViewModel.getAllMoviesFromDatabase()
            }

            smoothScroller.targetPosition = 0
            binding.recyclerViewMovies.layoutManager?.startSmoothScroll(smoothScroller)
        }
    }

    private fun filterListByText(text: String) {
        if (text.isNotEmpty()) {
            movieViewModel.getMoviesByTitle("%$text%")
        } else {
            movieViewModel.getAllMoviesFromDatabase()
        }
    }

    private fun onBookmarkClick(movieDb: MovieDb) {
        movieViewModel.bookMarkMovie(movieDb)
        if (movieDb.bookmarked) {
            val snackBar = Snackbar.make(
                binding.root,
                getString(R.string.film_added_to_bookmarks),
                Snackbar.LENGTH_LONG
            )
            snackBar.setAction(R.string.go_bookmarks) {
                findNavController().navigate(R.id.action_filmsFragment_to_bookmarksFragment)
            }
            snackBar.show()
        }
    }

    private fun applyMoviesRecyclerView() {
        movieAdapter = MovieAdapter(requireContext(), listOf(), onMovieClick = {
            val bundle = Bundle()
            bundle.putInt(MOVIE_ID, it)
            findNavController().navigate(R.id.action_filmsFragment_to_filmFragment, bundle)
        }, onBookmarkClick = {
            onBookmarkClick(it)
        })
        binding.recyclerViewMovies.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}