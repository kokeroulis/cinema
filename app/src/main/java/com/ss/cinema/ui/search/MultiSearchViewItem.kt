package com.ss.cinema.ui.search

import com.ss.cinema.domain.model.MultiSearch
import com.ss.cinema.domain.model.MultiSearchItem
import com.ss.cinema.util.mediatype.MediaType

sealed class MultiSearchViewItem {

    abstract val id: String

    data class Category(val mediaType: MediaType) : MultiSearchViewItem() {
        override val id: String = "Category#${mediaType.ordinal}"
    }

    data class Item(val item: MultiSearch) : MultiSearchViewItem() {

        override val id: String = "Item#${item.id}"
    }
}


fun List<MultiSearchItem>.toViewItem() = map {
    val items = mutableListOf<MultiSearchViewItem>()
    items += MultiSearchViewItem.Category(it.mediaType)
    items.addAll(
        it.searchResult.map(MultiSearchViewItem::Item)
    )
    items
}.flatten()


