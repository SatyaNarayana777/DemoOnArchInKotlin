package com.kotlin.demoinkotlin.ui.users

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kotlin.demoinkotlin.model.UserResponse
import com.kotlin.demoinkotlin.R
import com.kotlin.demoinkotlin.databinding.UsersListBinding

class UsersListAdapter : androidx.recyclerview.widget.RecyclerView.Adapter<UsersListAdapter.ViewHolder>() {

    private lateinit var usersList: List<UserResponse>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersListAdapter.ViewHolder {

        val binding: UsersListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.users_list, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersListAdapter.ViewHolder, position: Int) {
        holder.bind(usersList[position])
    }

    override fun getItemCount(): Int {
        return if (::usersList.isInitialized) usersList.size else 0
    }

    fun updateUsersList(usersList: List<UserResponse>) {
        this.usersList = usersList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: UsersListBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {

        fun bind(userResponse: UserResponse) {

            val viewModel = UserViewModel()

            viewModel.bind(userResponse)
            binding.viewModel = viewModel
        }
    }
}
