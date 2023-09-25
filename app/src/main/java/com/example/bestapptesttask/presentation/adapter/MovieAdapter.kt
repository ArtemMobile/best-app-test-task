package com.example.bestapptesttask.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bestapptesttask.R
import com.example.bestapptesttask.domain.model.MovieDb
import com.example.bestapptesttask.databinding.FilmCardBinding

class MovieAdapter(
    private val context: Context,
    private var films: List<MovieDb>,
    private val onBookmarkClick: (MovieDb) -> Unit,
    private val onMovieClick: (Int) -> Unit,
) :
    RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    class MovieHolder(val binding: FilmCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder(FilmCardBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateMovies(list: List<MovieDb>){
        films = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = films.size

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val film = films[position]
        with(holder.binding) {
            textViewTitle.text = film.title
            textViewRating.text = film.rating.toString()

            Glide.with(context)
                .load(film.image)
                .into(imageViewPreview)

            if (film.bookmarked) {
                imageViewBookmark.setImageResource(R.drawable.baseline_bookmark_remove_24)
            } else {
                imageViewBookmark.setImageResource(R.drawable.baseline_bookmark_add_24)
            }

            root.setOnClickListener{
                onMovieClick(film.id)
            }

            bookMarkField.setOnClickListener {
                film.bookmarked = !film.bookmarked
                onBookmarkClick(film)
                notifyItemChanged(position)
            }
        }
    }
}