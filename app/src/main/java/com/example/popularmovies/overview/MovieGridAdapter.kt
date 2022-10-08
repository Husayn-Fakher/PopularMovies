package com.example.popularmovies.overview

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.leanback.widget.DiffCallback
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.popularmovies.DetailsActivity
import com.example.popularmovies.databinding.GridViewItemBinding
import com.example.popularmovies.network.MovieDetails
import com.example.popularmovies.network.Results

class MovieGridAdapter : ListAdapter<Results,
        MovieGridAdapter.MovieViewHolder>(DiffCallback) {


    class MovieViewHolder(private var binding:
                          GridViewItemBinding
    ):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie : Results) {
            binding.movie = movie


            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
          //  binding.executePendingBindings()
        }

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieGridAdapter.MovieViewHolder {
        return MovieViewHolder(
            GridViewItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)        )    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)

        val intent = Intent(holder.itemView.context, DetailsActivity::class.java)
        intent.putExtra("overview", movie.overview)


        holder.itemView.setOnClickListener(){
            Toast.makeText(holder.itemView.context,movie.overview,Toast.LENGTH_LONG).show()

            holder.itemView.context.startActivity(intent)


        }

    }

    companion object DiffCallback : DiffUtil.ItemCallback<Results>() {
        override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem.poster_path == newItem.poster_path  // change later // or skip

        }

        override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem.poster_path == newItem.poster_path  // change later // or skip

        }
    }



}