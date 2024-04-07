package com.example.test_it_cron.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.test_it_cron.Model.App
import com.example.test_it_cron.ViewModel.UserViewModel
import com.example.test_it_cron.ViewModel.UserViewModelFactory
import com.example.test_it_cron.databinding.FragmentUserBinding
import javax.inject.Inject

class UserFragment : Fragment() {
    lateinit var viewModel: UserViewModel
    @Inject
    lateinit var viewModelFactory: UserViewModelFactory
    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity!!.application as App).getComponent().injectUser(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(UserViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = this.arguments
        val id = bundle?.getLong("id")

        viewModel.getUser(id.toString())
        viewModel.user.observe(this, Observer {
            Glide.with(this).load(it.avatar_url).into(binding.imgAvatar)
            binding.txtName.text = "Name: " + it.login
            val email = it.email?:""
            binding.txtEmail.text = "EMail: " + email
            val company = it.company?:""

            binding.txtCompany.text = "Организация: " + company
            binding.txtFollowing.text = "Подписчики: " + it.following.toString()
            binding.txtFollowers.text = "Подписан: " + it.followers.toString()
            binding.txtCreatedAt.text = "Создан: " + it.created_at?.replace("T", " ")?.substring(0, it.created_at.length - 4)
        })
    }
}

