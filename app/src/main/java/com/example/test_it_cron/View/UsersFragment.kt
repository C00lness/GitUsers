package com.example.test_it_cron.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.test_it_cron.Model.RVAdapter
import com.example.test_it_cron.R
import com.example.test_it_cron.ViewModel.UsersViewModel
import java.util.*
import androidx.lifecycle.Observer
import com.example.test_it_cron.Model.App
import com.example.test_it_cron.ViewModel.UsersViewModelFactory
import com.example.test_it_cron.databinding.FragmentUsersBinding
import javax.inject.Inject

class UsersFragment : Fragment() {
    lateinit var viewModel: UsersViewModel
    @Inject
    lateinit var viewModelFactory: UsersViewModelFactory
    private var isLastPage: Boolean = false
    private var isLoading: Boolean = false
    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as App).getComponent().injectUsers(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(UsersViewModel::class.java)
        viewModel.getUsers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val swipeRefreshLayout: SwipeRefreshLayout = view.findViewById(R.id.container)

        val recyclerView = binding.recycle
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = RVAdapter()
        recyclerView.adapter = adapter

        viewModel.users.observe(viewLifecycleOwner, Observer {
            it.let {
                adapter.refresh(it)
                swipeRefreshLayout.setOnRefreshListener {
                    swipeRefreshLayout.isRefreshing = false
                    Collections.shuffle(it, Random(System.currentTimeMillis()))
                    adapter.refresh(it)
                }
                recyclerView.addOnScrollListener(object : Pagination(recyclerView.layoutManager as LinearLayoutManager) {
                    override fun isLastPage(): Boolean = isLastPage
                    override fun isLoading(): Boolean = isLoading
                    override fun loadMoreItems() = adapter.refresh(it)
                })
            }
        })
    }
}