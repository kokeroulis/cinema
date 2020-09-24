package com.ss.cinema.ui.movies

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ss.cinema.data.Resource
import com.ss.cinema.data.map
import com.ss.cinema.domain.model.Movie
import com.ss.cinema.domain.usecase.FetchPopularMoviesUseCase
import com.ss.cinema.util.ReactiveViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class MoviesViewModel @ViewModelInject constructor(
    private val fetchPopularMoviesUseCase: FetchPopularMoviesUseCase
) : ReactiveViewModel() {

    init {
        fetchPopularMovies()
    }

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() {
            return _movies
        }

    private fun fetchPopularMovies() {
        fetchPopularMoviesUseCase.fetchPopularMovies()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { resource ->
                if (resource is Resource.Success) {
                    resource.map {
                        _movies.value = it
                    }
                }
            }
            .also {
                disposable.add(it)
            }
    }
}