package com.example.test_it_cron.Model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test_it_cron.R
import com.example.test_it_cron.databinding.ItemBinding

class RVAdapter(): RecyclerView.Adapter<RVAdapter.Holder>() {
    private  var users: List<Users> = listOf()
    class Holder(val binding: ItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(u: Users) = with(itemView){
            binding.txtId.text = u.id.toString()
            binding.txtLogin.text = u.login
            Glide.with(this).load(u.avatar_url).into(binding.imgAvatar)

            binding.cardView.setOnClickListener{
                val bundle = Bundle()
                bundle.putLong("id", u.id)
                findNavController()
                    .navigate(R.id.action_usersFragment_to_userFragment, bundle)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
        Holder(ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind(users[position])
    override fun getItemCount(): Int = users.size

    fun refresh(u: List<Users>) {
        this.users = u
        notifyItemRangeChanged(0, 10)
    }
}