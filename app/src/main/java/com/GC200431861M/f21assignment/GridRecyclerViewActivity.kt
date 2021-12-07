package com.GC200431861M.f21assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

        setSupportActionBar(binding.mainToolBar.toolbar)
    }

    /**
     * Add the menu to the toolbar
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // when is like switch
        when(item.itemId){
            R.id.addChampion -> {
                startActivity(Intent(applicationContext, MainActivity::class.java))
                return true
            }
            R.id.championList -> {
                //disable this be we dont want to load the page again
                //startActivity(Intent(applicationContext, GridRecyclerViewActivity::class.java))
                return true
            }
        }
        return  super.onOptionsItemSelected(item)
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