package com.example.f21assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.f21assignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Toast.makeText(this, "Champion has been added", Toast.LENGTH_LONG).show()

        // when the button is clicked
        binding.addChampionFAB.setOnClickListener {
            // And all the text fields have data/values in them
            if(binding.championNameEditText.text.toString().isNotEmpty() &&
                binding.championPassiveAbilityEditText.toString().isNotEmpty() &&
                binding.championAbilityOneEditText.text.toString().isNotEmpty() &&
                binding.championAbilityTwoEditText.text.toString().isNotEmpty() &&
                binding.championAbilityThreeEditText.text.toString().isNotEmpty() &&
                binding.championAbilityFourEditText.text.toString().isNotEmpty()) {

            //add information to the database


                // if the data when through
                // clear the fields
                // send the user back to the page before
                // make a toast to tell them what happened
            } else {
                // make a toast to tell them what happened
                Toast.makeText(this, "All the Fields are Required", Toast.LENGTH_LONG).show()
            }
        }
    }
}