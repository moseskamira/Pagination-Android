package com.example.paginationandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paginationandroid.adapter.MovieAdapter
import com.example.paginationandroid.viewModel.MovieViewModel
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {
    private lateinit var moviewRecyclerView: RecyclerView
    lateinit var movieAdapter: MovieAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        moviewRecyclerView = findViewById(R.id.movie_recycler_view)
        initializeRecyclerView()
        initializeViewModel()
    }

    private fun initializeRecyclerView() {
        moviewRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val dividerItemDecoration = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(dividerItemDecoration)
            movieAdapter = MovieAdapter()
            adapter = movieAdapter

        }
    }

    private fun initializeViewModel() {
        val viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        lifecycleScope.launchWhenCreated {
            viewModel.returnMovies().collectLatest {
                movieAdapter.submitData(it)
            }
        }
    }



}