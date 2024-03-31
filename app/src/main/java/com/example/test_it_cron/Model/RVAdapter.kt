package com.example.test_it_cron.Model

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test_it_cron.R
import com.example.test_it_cron.View.UserFragment


class RVAdapter(): RecyclerView.Adapter<RVAdapter.Holder>() {
    private  var users: List<User> = listOf()
    class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(u: User) = with(itemView){
            itemView.findViewById<TextView>(R.id.txt_id).text = u.id.toString()
            itemView.findViewById<TextView>(R.id.txt_login).text = u.login
            Glide.with(this).load(u.avatar_url).into(itemView.findViewById<ImageView>(R.id.img_avatar))

            itemView.findViewById<CardView>(R.id.card_view).setOnClickListener{
                val bundle = Bundle()
                bundle.putLong("id", u.id)
                findNavController()
                    .navigate(R.id.action_usersFragment_to_userFragment, bundle)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size
    fun refresh(u: List<User>) {
        this.users = u
        notifyItemRangeChanged(0, 10)
    }
}