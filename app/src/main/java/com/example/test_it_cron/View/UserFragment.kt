package com.example.test_it_cron.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.test_it_cron.R
import com.example.test_it_cron.ViewModel.GitViewModel
import com.example.test_it_cron.ViewModel.UserViewModel
import java.text.Format
import java.util.*

class UserFragment : Fragment() {
    lateinit var viewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = this.arguments
        val id = bundle?.getLong("id")

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.getDetails(id.toString())

        viewModel.details.observe(this, Observer {
            Glide.with(this).load(it.avatar_url).into(view.findViewById<ImageView>(R.id.img_avatar))
            view.findViewById<TextView>(R.id.txt_name).text = "Name: " + it.login
            val email = it.email?:""
            view.findViewById<TextView>(R.id.txt_email).text = "EMail: " + email
            val company = it.company?:""
            view.findViewById<TextView>(R.id.txt_company).text = "Организация: " + company
            view.findViewById<TextView>(R.id.txt_following).text = "Подписчики: " + it.following.toString()
            view.findViewById<TextView>(R.id.txt_followers).text = "Подписан: " + it.followers.toString()
            view.findViewById<TextView>(R.id.txt_created_at).text = "Создан: " + it.created_at?.replace("T", " ")?.substring(0, it.created_at.length - 4)
        })
    }
}

