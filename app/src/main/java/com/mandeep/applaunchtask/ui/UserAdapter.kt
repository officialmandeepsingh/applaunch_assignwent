package com.mandeep.applaunchtask.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mandeep.applaunchtask.data.local.User
import com.mandeep.applaunchtask.databinding.ItemUserDetailsBinding
import com.mandeep.applaunchtask.util.extension.onClick

class UserAdapter(val onClick: (userId: Int) -> Unit, val onDelete: (user: User) -> Unit) :
    ListAdapter<User, UserAdapter.MyViewHolder>(UserDiffUtil) {
    private val TAG = UserAdapter::class.java.simpleName

    inner class MyViewHolder(val binding: ItemUserDetailsBinding) :
        RecyclerView.ViewHolder(binding.root)


    object UserDiffUtil : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: User,
            newItem: User
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(
            ItemUserDetailsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        Log.d(TAG, "onBindViewHolder() called with: holder = $user, position = $position")
        holder.binding.apply {
            tvFirstName.text = user.firstName
            tvLastName.text = user.lastName
            tvEmailId.text = user.emailId
            ivDeleteUser onClick { onDelete.invoke(user) }
        }



        holder.binding.root onClick {
            onClick.invoke(user.id)
        }

    }


}