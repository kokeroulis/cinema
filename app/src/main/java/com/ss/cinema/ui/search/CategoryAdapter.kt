package com.ss.cinema.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ss.cinema.R
import com.ss.cinema.databinding.ListItemSearchCategoryBinding
import com.ss.cinema.domain.viewstate.SearchCategoryViewState
import com.ss.cinema.util.mediatype.MediaType
import javax.inject.Inject

class CategoryAdapter @Inject constructor() : ListAdapterDelegate.DelegateAdapter<
        MultiSearchViewItem.Category, CategoryAdapter.MultiCategoryViewHolder> {

    override val itemViewType: Int = R.layout.list_item_search_category

    override fun isForViewType(item: Any, position: Int): Boolean {
        return item is MultiSearchViewItem.Category
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultiCategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemSearchCategoryBinding.inflate(layoutInflater, parent, false)
        return MultiCategoryViewHolder(binding)
    }

    override fun bind(
        item: MultiSearchViewItem.Category,
        holder: MultiCategoryViewHolder,
        position: Int
    ) {
        holder.bind(item.mediaType)
    }

    class MultiCategoryViewHolder(private val binding: ListItemSearchCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MediaType) {
            with(binding) {
                viewState = SearchCategoryViewState(item)
                executePendingBindings()
            }
        }
    }
}