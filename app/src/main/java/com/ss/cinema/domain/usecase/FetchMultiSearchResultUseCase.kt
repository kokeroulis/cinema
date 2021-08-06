package com.ss.cinema.domain.usecase

import com.ss.cinema.data.Resource
import com.ss.cinema.data.map
import com.ss.cinema.data.repository.SearchRepository
import com.ss.cinema.domain.mapper.MultiSearchMapper
import com.ss.cinema.domain.model.MultiSearch
import com.ss.cinema.domain.model.MultiSearchItem
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class FetchMultiSearchResultUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
    private val multiSearchMapper: MultiSearchMapper
) {
    fun fetchSearchResult(queryValue: String): Single<Resource<List<MultiSearchItem>>> {
        return searchRepository.fetchSearchResult(queryValue)
            .map { resource ->
                resource.map { baseResponse ->
                    multiSearchMapper.mapFrom(baseResponse)
                }.map { it.toSearchItem() }
            }
    }

    private fun List<MultiSearch>.toSearchItem() = groupBy { it.mediaType }.let { responsePerType ->
        responsePerType.map { (key, value) -> MultiSearchItem(key, value) }
    }
}