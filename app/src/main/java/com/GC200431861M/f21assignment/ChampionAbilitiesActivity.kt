package com.GC200431861M.f21assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.GC200431861M.f21assignment.databinding.ActivityChampionAbilitiesBinding

class ChampionAbilitiesActivity : AppCompatActivity() {
    private lateinit var binding : ActivityChampionAbilitiesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChampionAbilitiesBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //we need to pull the data from the GridViewActivity (since we pushed it from the GridViewActivity it is now able to be grabbed)
        //intent.getStringExtra(//this part must match what the field is called when its being sent (// in this case from GridViewActivity and has championName))
        binding.championNameTextView.text = intent.getStringExtra("championName")
        binding.championPassiveAbilityTextView.text = intent.getStringExtra("passive")
        binding.championAbilityOneTextView.text = intent.getStringExtra("abilityOne")
        binding.championAbilityTwoTextView.text = intent.getStringExtra("abilityTwo")
        binding.championAbilityThreeTextView.text = intent.getStringExtra("abilityThree")
        binding.championAbilityFourTextView.text = intent.getStringExtra("abilityFour")

//        //when the button is clicked
//        binding.backFAB.setOnClickListener{
//            // start new activity and send the user there
//            startActivity(Intent(this, GridRecyclerViewActivity::class.java))
//        }


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
                startActivity(Intent(applicationContext, GridRecyclerViewActivity::class.java))
                return true
            }
        }
        return  super.onOptionsItemSelected(item)
    }
}