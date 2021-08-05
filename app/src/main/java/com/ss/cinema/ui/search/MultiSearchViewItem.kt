package com.ss.cinema.ui.search

import com.ss.cinema.domain.model.MultiSearch

data class MultiSearchViewItem(val searchItem: MultiSearch)

fun List<MultiSearch>.toViewItem() = map {
    MultiSearchViewItem(it)
}


