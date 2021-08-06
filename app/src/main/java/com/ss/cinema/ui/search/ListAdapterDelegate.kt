package com.ss.cinema.ui.search

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ListAdapterDelegate<T : Any>(
    diffCallback: DiffUtil.ItemCallback<T>,
    private val adapterDelegates: List<DelegateAdapter<out T, out RecyclerView.ViewHolder>>
) : ListAdapter<T, RecyclerView.ViewHolder>(diffCallback) {

    init {
        validateDelegates()
    }

    interface DelegateAdapter<T, VH : RecyclerView.ViewHolder> {

        val itemViewType: Int

        fun isForViewType(item: Any, position: Int): Boolean

        fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH

        fun bind(item: T, holder: VH, position: Int)
    }

    @Suppress("UNCHECKED_CAST")
    private fun findAdapterForViewType(viewType: Int): DelegateAdapter<T, RecyclerView.ViewHolder> {
        return adapterDelegates.first { it.itemViewType == viewType } as DelegateAdapter<T, RecyclerView.ViewHolder>
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return adapterDelegates.find { it.isForViewType(item, position) }!!.itemViewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val adapter = findAdapterForViewType(viewType)
        return adapter.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        val adapter = findAdapterForViewType(holder.itemViewType)
        adapter.bind(item, holder, position)
    }


    private fun validateDelegates() {
        adapterDelegates.forEach { delegate ->
            require(adapterDelegates.filter { it.itemViewType == delegate.itemViewType }.size == 1) {
                "Multiple Delegates are using the same view type. Each Delegate should have its own itemViewType"
            }
        }
    }
}