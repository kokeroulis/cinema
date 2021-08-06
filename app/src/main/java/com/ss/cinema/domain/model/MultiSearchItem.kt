package com.ss.cinema.domain.model

import com.ss.cinema.util.mediatype.MediaType

data class MultiSearchItem(
    var mediaType: MediaType,
    val searchResult: List<MultiSearch>,
)
