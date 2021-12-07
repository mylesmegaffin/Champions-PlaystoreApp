package com.GC200431861M.f21assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.GC200431861M.f21assignment.databinding.ActivityGridRecyclerViewBinding

class GridRecyclerViewActivity : AppCompatActivity(), GridViewAdapter.ChampionItemListener {
    private lateinit var binding : ActivityGridRecyclerViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGridRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //get the data from the view model
        val viewModel : ChampionListViewModel by viewModels()
        // observe is an active listener that waits for any change to the data and updates it to the activity (real time)
        viewModel.getChampions().observe(this, { champions ->
            // creating an adapter with(context, list of champions)
            var gridViewAdapter = GridViewAdapter(this, champions, this)
            binding.gridRecyclerView.adapter = gridViewAdapter
        })

        //when the fab is clicked we want to navigate to the add champion view
        binding.addChampionFAB.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java));
        }
    }

    /**
     * When a champion is selected, pass the Champion information to the ChampionAbilitiesActivity
     */
    //what do we want it to do when a champion gets clicked
    override fun championSelected(champion: Champion) {
        val intent = Intent(this, ChampionAbilitiesActivity::class.java)
        //when we go to a new activity we want information to be passes into another
        intent.putExtra("championID", champion.id)
        intent.putExtra("championName", champion.name)
        intent.putExtra("passive", champion.passive)
        intent.putExtra("abilityOne", champion.abilityOne)
        intent.putExtra("abilityTwo", champion.abilityTwo)
        intent.putExtra("abilityThree", champion.abilityThree)
        intent.putExtra("abilityFour", champion.abilityFour)

        // Which the activity
        startActivity(intent)
    }

}