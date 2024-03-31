package com.example.test_it_cron.View

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.test_it_cron.Model.RVAdapter
import com.example.test_it_cron.R
import com.example.test_it_cron.ViewModel.GitViewModel
import java.util.*
import androidx.lifecycle.Observer

class UsersFragment : Fragment() {
    private lateinit var viewModel: GitViewModel
    private var isLastPage: Boolean = false
    private var isLoading: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GitViewModel::class.java)
        viewModel.getUsers()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val swipeRefreshLayout: SwipeRefreshLayout = view.findViewById(R.id.container)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycle)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = RVAdapter()
        recyclerView.adapter = adapter

        viewModel.users.observe(this, Observer {
            it.let {
                adapter.refresh(it)
                swipeRefreshLayout.setOnRefreshListener {
                    swipeRefreshLayout.isRefreshing = false
                    Collections.shuffle(it, Random(System.currentTimeMillis()))
                    adapter.refresh(it)
                }
                recyclerView.addOnScrollListener(object : Pagination(recyclerView.layoutManager as LinearLayoutManager) {
                    override fun isLastPage(): Boolean {
                        return isLastPage
                    }

                    override fun isLoading(): Boolean {
                        return isLoading
                    }

                    override fun loadMoreItems() {
                        adapter.refresh(it)
                    }
                })
            }
        })
    }


}