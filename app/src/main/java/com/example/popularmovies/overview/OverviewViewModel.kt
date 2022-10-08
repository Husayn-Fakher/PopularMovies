package com.example.popularmovies.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.popularmovies.network.MovieDetails
import com.example.popularmovies.network.MoviesAPI
import com.example.popularmovies.network.Results

enum class MovieApiStatus { LOADING, ERROR, DONE }


class OverviewViewModel : ViewModel(){
    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<MovieApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<MovieApiStatus> = _status

    private val _movies = MutableLiveData<MovieDetails>()
    val movies: LiveData<MovieDetails> = _movies

    private val _movieDetails = MutableLiveData<List<Results>>()
    val movieDetails: LiveData<List<Results>> = _movieDetails

    /**
     * Call getMovies() on init so we can display status immediately.
     */
    init {
        getMovies()
    }

    private fun getMovies() {

       viewModelScope.launch {
           _status.value = MovieApiStatus.LOADING

           try {


                val Result = MoviesAPI.retrofitService.getMovies()

                _movies.value = Result

                _status.value = MovieApiStatus.DONE

               _movieDetails.value = Result.results

           } catch (e: Exception) {
               _status.value = MovieApiStatus.ERROR

           }
       }

    }
}