package com.example.f21assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.f21assignment.databinding.ActivityGridRecyclerViewBinding
import com.example.f21assignment.databinding.ActivityRecyclerListBinding

class GridRecyclerViewActivity : AppCompatActivity() {
    private lateinit var binding : ActivityGridRecyclerViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGridRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //get the data from the view model
        val viewModel : ChampionListViewModel by viewModels()
        viewModel.getChampions().observe(this, { champions ->
            // creating an adapter with(context, list of champions)
            var gridViewAdapter = GridViewAdapter(this, champions)
            binding.gridRecyclerView.adapter = gridViewAdapter
        })
    }
}