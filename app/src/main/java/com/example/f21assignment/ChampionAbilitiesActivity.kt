package com.example.f21assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.f21assignment.databinding.ActivityChampionAbilitiesBinding

class ChampionAbilitiesActivity : AppCompatActivity() {
    private lateinit var binding : ActivityChampionAbilitiesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChampionAbilitiesBinding.inflate(layoutInflater)
        setContentView(binding.root)





        //when the button is clicked
        binding.backFAB.setOnClickListener{
            // start new activity and send the user there
            startActivity(Intent(this, GridRecyclerViewActivity::class.java))
        }

    }
}