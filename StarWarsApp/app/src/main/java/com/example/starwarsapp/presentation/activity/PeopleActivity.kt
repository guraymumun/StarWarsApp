package com.example.starwarsapp.presentation.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwarsapp.R
import com.example.starwarsapp.databinding.ActivityPeopleBinding
import com.example.starwarsapp.presentation.adapter.PeoplePagingAdapter
import com.example.starwarsapp.presentation.viewmodel.PeopleViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class PeopleActivity : BaseActivity<ActivityPeopleBinding, PeopleViewModel>(R.layout.activity_people) {

    private val viewModel: PeopleViewModel by viewModel()
    private lateinit var pagingAdapter: PeoplePagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        setPagingRecyclerView()
        setObservers()
    }


    private fun setPagingRecyclerView() {
        pagingAdapter = PeoplePagingAdapter(this)
        binding.recyclerView.adapter = pagingAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setObservers() {
        lifecycleScope.launch {
            pagingAdapter.loadStateFlow.collectLatest { loadStates ->
                viewModel.setProgressVisible(loadStates.refresh is LoadState.Loading || loadStates.append is LoadState.Loading)

                binding.noResults.visibility = if (loadStates.refresh is LoadState.NotLoading && pagingAdapter.itemCount == 0)
                    View.VISIBLE
                else
                    View.GONE
            }
        }

        binding.submit.setOnClickListener {
            lifecycleScope.launch {
                viewModel.getNextPeople(binding.searchEditText.text.toString().trim()).collect {
                    pagingAdapter.submitData(it)
                }
            }
        }

        viewModel.noInternet.observe(this) {
            Toast.makeText(this, R.string.no_internet, Toast.LENGTH_LONG).show()
        }
    }

    override fun getVM(): PeopleViewModel = viewModel
}