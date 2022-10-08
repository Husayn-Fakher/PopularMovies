package com.example.popularmovies

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.popularmovies.network.MovieDetails
import com.example.popularmovies.network.Results
import com.example.popularmovies.overview.MovieApiStatus
import com.example.popularmovies.overview.MovieGridAdapter

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {

    imgUrl?.let {
        val prefix = "https://image.tmdb.org/t/p/original/"
        val imgUri = (prefix+imgUrl).toUri().buildUpon().scheme("https").build()
        Log.d("imageUrl", prefix+imgUrl)
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                     data: List<Results>?) {

    val adapter = recyclerView.adapter as MovieGridAdapter
    adapter.submitList(data)

}


@BindingAdapter("movieApiStatus")
fun bindStatus(statusImageView: ImageView,
               status: MovieApiStatus?) {

    when (status) {
        MovieApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }

        MovieApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }

        MovieApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

class BindingAdapters {




}