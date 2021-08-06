package com.ss.cinema.ui.search

import androidx.recyclerview.widget.DiffUtil

class MultiSearchViewItemDiffCallback : DiffUtil.ItemCallback<MultiSearchViewItem>() {

    override fun areItemsTheSame(oldItem: MultiSearchViewItem, newItem: MultiSearchViewItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MultiSearchViewItem, newItem: MultiSearchViewItem): Boolean {
        return oldItem == newItem
    }
}