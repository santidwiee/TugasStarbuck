package com.example.tugasstarbuck.menu

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasstarbuck.R
import kotlinx.android.synthetic.main.activity_menu.*

class MenuListActivity :AppCompatActivity() {

    private lateinit var viewModel: MenuListViewModel
    private lateinit var menuListAdapter: MenuListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        viewModel = ViewModelProviders.of(this)
            .get(MenuListViewModel::class.java)
        initAdapter()
        initState()
    }

    private fun initAdapter(){
        menuListAdapter = MenuListAdapter { viewModel.retry() }
        recycler_list.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycler_list.adapter = menuListAdapter
        viewModel.menuList.observe(this, Observer{
            menuListAdapter.submitList(it)
        })
    }

    private fun initState(){
        txt_error.setOnClickListener { viewModel.retry() }
        viewModel.getState().observe(this, Observer { state ->
            progress_bar.visibility = if (viewModel.listIsEmpty() && state == State.LOADING)
                View.VISIBLE else View.GONE
            txt_error.visibility = if (viewModel.listIsEmpty() && state == State.ERROR)
                View.VISIBLE else View.GONE
            if (!viewModel.listIsEmpty()){
                menuListAdapter.setState(state ?: State.DONE)
            }
        })
    }
}