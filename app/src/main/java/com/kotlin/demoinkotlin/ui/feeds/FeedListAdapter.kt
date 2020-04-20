package com.kotlin.demoinkotlin.ui.feeds

import androidx.paging.PagedListAdapter
import androidx.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kotlin.demoinkotlin.model.FeedsReponse
import com.kotlin.demoinkotlin.R
import com.kotlin.demoinkotlin.databinding.FeedsListBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class FeedListAdapter : PagedListAdapter<FeedsReponse, FeedListAdapter.ViewHolder>(feedsDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: FeedsListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.feeds_list, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val result: FeedsReponse = getItem(position)!!

        holder.bind(result)
    }


    class ViewHolder(private val binding: FeedsListBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {

        fun bind(feedsReponse: FeedsReponse) {

            val viewModel = FeedViewModel()
            viewModel.bind(feedsReponse)
            binding.viewModel = viewModel
        }
    }

    companion object {
        val feedsDiffCallback = object : DiffUtil.ItemCallback<FeedsReponse>() {
            override fun areItemsTheSame(oldItem: FeedsReponse, newItem: FeedsReponse): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: FeedsReponse, newItem: FeedsReponse): Boolean {
                return oldItem == newItem
            }
        }
    }

}
