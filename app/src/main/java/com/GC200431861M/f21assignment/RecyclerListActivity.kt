package com.GC200431861M.f21assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.GC200431861M.f21assignment.databinding.ActivityRecyclerListBinding

class RecyclerListActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRecyclerListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //get the data from the view model
        val viewModel : ChampionListViewModel by viewModels()
        viewModel.getChampions().observe(this, { champions ->
            // creating an adapter with(context, list of champions)
            var recyclerViewAdapter = RecyclerViewAdapter(this, champions)
            binding.gridRecyclerView.adapter = recyclerViewAdapter
        })
    }
}