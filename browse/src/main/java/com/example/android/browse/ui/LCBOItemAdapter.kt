package com.example.android.browse.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.example.android.browse.R
import com.example.android.core.AppExecutors
import com.example.android.core.databinding.LcboItemListItemBinding
import com.example.android.core.ui.DataBoundListAdapter
import com.example.android.core.vo.LCBOItem
import com.example.android.core.vo.LCBOItemDiffCallback

/**
 * A RecyclerView adapter for [LCBOItem] class.
 */
class LCBOItemAdapter(
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: AppExecutors
) : DataBoundListAdapter<LCBOItem, LcboItemListItemBinding>(
    appExecutors = appExecutors,
    diffCallback = LCBOItemDiffCallback.callback
) {

    override fun createBinding(parent: ViewGroup): LcboItemListItemBinding {
        val binding = DataBindingUtil.inflate<LcboItemListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.lcbo_item_list_item,
            parent,
            false,
            dataBindingComponent
        )
        return binding
    }

    override fun bind(binding: LcboItemListItemBinding, item: LCBOItem) {
        binding.lcboItem = item }
}