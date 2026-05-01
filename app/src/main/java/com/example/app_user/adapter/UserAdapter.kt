package com.example.app_user.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_user.adapter.UserAdapter.*
import com.example.app_user.databinding.ItemListBinding
import com.example.app_user.model.User

class UserAdapter(
    private val list: List<User>,
    private val  onEdit: (User) -> Unit,
    private val  onDelete: (User) -> Unit,
): RecyclerView.Adapter<UserViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {

        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: UserViewHolder,
        position: Int
    ) {
        val user = list[position]
        holder.binding.name.text = user.name
        holder.binding.dob.text = user.dob
        holder.binding.address.text = user.address
        holder.binding.phone.text = user.phone
        holder.binding.email.text = user.email
        holder.binding.editBtn.setOnClickListener {
            onEdit(user)
        }
        holder.binding.deleteBtn.setOnClickListener {
            onDelete(user)
        }
    }

    override fun getItemCount(): Int = list.size

    inner class UserViewHolder (val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root)
}