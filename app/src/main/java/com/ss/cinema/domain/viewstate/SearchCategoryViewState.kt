package com.ss.cinema.domain.viewstate

import com.ss.cinema.util.extensions.getIcon
import com.ss.cinema.util.mediatype.MediaType

class SearchCategoryViewState(private val mediaType: MediaType) {

    fun getId(): Int = mediaType.ordinal

    fun getMediaType() = mediaType.name

    fun getIcon(): Int = mediaType.getIcon()
}