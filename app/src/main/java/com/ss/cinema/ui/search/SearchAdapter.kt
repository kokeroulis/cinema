package com.ss.cinema.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ss.cinema.R
import com.ss.cinema.databinding.ListItemSearchBinding
import com.ss.cinema.domain.model.MultiSearch
import com.ss.cinema.domain.viewstate.SearchViewState
import javax.inject.Inject

class SearchAdapter @Inject constructor() : ListAdapterDelegate.DelegateAdapter<
        MultiSearchViewItem, SearchAdapter.MultiSearchViewHolder> {

    private lateinit var searchHandler: SearchHandler

    fun setSearchHandler(handler: SearchHandler) {
        searchHandler = handler
    }

    override val itemViewType: Int = R.layout.list_item_search

    override fun isForViewType(item: Any, position: Int): Boolean {
        return item is MultiSearchViewItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultiSearchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemSearchBinding.inflate(layoutInflater, parent, false)
        return MultiSearchViewHolder(binding)
    }

    override fun bind(
        item: MultiSearchViewItem, holder: MultiSearchViewHolder, position: Int
    ) {
        holder.bind(item.searchItem, searchHandler)
    }

    class MultiSearchViewHolder(private val binding: ListItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MultiSearch, searchHandler: SearchHandler) {
            with(binding) {
                viewState = SearchViewState(item)
                handler = searchHandler
                executePendingBindings()
            }
        }
    }
}