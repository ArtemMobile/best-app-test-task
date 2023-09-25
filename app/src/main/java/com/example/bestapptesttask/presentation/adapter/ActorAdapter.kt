package com.example.bestapptesttask.presentation.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bestapptesttask.databinding.ActorCardBinding
import com.example.bestapptesttask.domain.model.Person

class ActorAdapter(
    private val context: Context,
    private var persons: List<Person>,
) :
    RecyclerView.Adapter<ActorAdapter.MovieHolder>() {

    class MovieHolder(val binding: ActorCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder(ActorCardBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int = persons.size

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val person = persons[position]
        with(holder.binding) {
            textViewAsCharacter.text = person.asCharacter
            textViewActorName.text = person.name
            Glide.with(context)
                .load(person.image)
                .into(imageViewActor)
        }
    }
}