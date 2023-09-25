package com.example.bestapptesttask.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bestapptesttask.R
import com.example.bestapptesttask.domain.model.MovieDb
import com.example.bestapptesttask.databinding.FragmentBookmarksBinding
import com.example.bestapptesttask.presentation.adapter.MovieAdapter
import com.example.bestapptesttask.presentation.viewmodel.MovieViewModel
import com.example.bestapptesttask.utils.Constants
import com.example.bestapptesttask.utils.RxSearch
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

@AndroidEntryPoint
class BookmarksFragment : Fragment() {

    private val binding: FragmentBookmarksBinding by lazy {
        FragmentBookmarksBinding.inflate(layoutInflater)
    }

    private val movieViewModel by viewModels<MovieViewModel>()

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieViewModel.getBookmarkedMoviesFromDatabase()
        setObservers()
        applyMoviesRecyclerView()
        applyClicks()
        applySearchView()
    }

    private fun applyClicks(){
        binding.apply {
            deleteAllBookmarksCard.setOnClickListener{
                movieViewModel.deleteAllBookMarks()
                movieViewModel.getBookmarkedMoviesFromDatabase()
            }
            editTextSearch.setOnCloseListener {
                editTextSearch.clearFocus()
                true
            }
        }
    }

    private fun setObservers() {
        movieViewModel.bookmarkedMovies.observe(viewLifecycleOwner) {
            movieAdapter.updateMovies(it)
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

    private fun filterListByText(text: String) {
        if (text.isNotEmpty()) {
            movieViewModel.getBookmarkedMoviesByTitle("%$text%")
        } else {
            movieViewModel.getBookmarkedMoviesFromDatabase()
        }
    }

    private fun onBookmarkClick(movieDb: MovieDb) {
        movieViewModel.bookMarkMovie(movieDb)
        movieViewModel.getBookmarkedMoviesFromDatabase()
    }

    private fun applyMoviesRecyclerView() {
        movieAdapter = MovieAdapter(requireContext(), listOf(), onMovieClick = {
            val bundle = Bundle()
            bundle.putInt(Constants.MOVIE_ID, it)
            findNavController().navigate(R.id.action_bookmarksFragment_to_filmFragment, bundle)
        }, onBookmarkClick = {
            onBookmarkClick(it)
        })
        binding.recyclerViewBookmarkMovies.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}