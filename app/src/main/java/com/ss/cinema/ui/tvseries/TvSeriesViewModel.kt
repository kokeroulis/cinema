package com.ss.cinema.ui.tvseries

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ss.cinema.data.Resource
import com.ss.cinema.data.map
import com.ss.cinema.domain.model.TvSeries
import com.ss.cinema.domain.usecase.FetchPopularTvSeriesUseCase
import com.ss.cinema.util.ReactiveViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class TvSeriesViewModel @ViewModelInject constructor(
    private val fetchPopularTvSeriesUseCase: FetchPopularTvSeriesUseCase
) : ReactiveViewModel() {

    init {
        fetchPopularTvSeries()
    }

    private val _tvSeries = MutableLiveData<List<TvSeries>>()
    val tvSeries: LiveData<List<TvSeries>>
        get() = _tvSeries

    private fun fetchPopularTvSeries() {
        fetchPopularTvSeriesUseCase.fetchPopularTvSeries()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { resource ->
                if (resource is Resource.Success) {
                    resource.map {
                        _tvSeries.value = it
                    }
                }
            }
            .also {
                disposable.add(it)
            }
    }
}