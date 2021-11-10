package com.example.f21assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.f21assignment.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // when the button is clicked
        binding.addChampionFAB.setOnClickListener {
            // And all the text fields have data/values in them
            if(binding.championNameEditText.text.toString().isNotEmpty() &&
                binding.championPassiveAbilityEditText.toString().isNotEmpty() &&
                binding.championAbilityOneEditText.text.toString().isNotEmpty() &&
                binding.championAbilityTwoEditText.text.toString().isNotEmpty() &&
                binding.championAbilityThreeEditText.text.toString().isNotEmpty() &&
                binding.championAbilityFourEditText.text.toString().isNotEmpty()) {

                // create an instance of the champion
                val champ = Champion()

                // we need to pull the information out of the activity and insert it into the class (Champion Class)
                champ.name = binding.championNameEditText.text.toString()
                champ.passive = binding.championPassiveAbilityEditText.text.toString()
                champ.abilityOne = binding.championAbilityOneEditText.text.toString()
                champ.abilityTwo = binding.championAbilityTwoEditText.text.toString()
                champ.abilityThree = binding.championAbilityThreeEditText.text.toString()
                champ.abilityFour = binding.championAbilityFourEditText.text.toString()

                //add information to the database

                // Connecting to the database (the collection)
                val db = FirebaseFirestore.getInstance().collection("champions")

                //1. get an ID from FireStore and setting it to the object/class
                champ.id = db.document().id

                //2. store the champ as a document (!! = this is saying that it wont be null (because we just got it on the line above))
                db.document(champ.id!!).set(champ)
                        // when the document is successful
                        .addOnSuccessListener{
                            Toast.makeText(this, "Champion Add", Toast.LENGTH_LONG).show()

                            // Resetting the interface
                            // clearing the fields so the users can add more data
                            binding.championNameEditText.setText("")
                            binding.championPassiveAbilityEditText.setText("")
                            binding.championAbilityOneEditText.setText("")
                            binding.championAbilityTwoEditText.setText("")
                            binding.championAbilityThreeEditText.setText("")
                            binding.championAbilityFourEditText.setText("")
                        }
                        .addOnFailureListener{
                            Toast.makeText(this, "FAILURE, Champion NOT Add (DB Error)", Toast.LENGTH_LONG).show()
                            //Logging the exception into logcat under the tag "DB Message"
                            var message = it.localizedMessage
                            message?.let {
                                Log.i("DB Message", message)
                            }
                        }

                // if the data when through
                // clear the fields
                // send the user back to the page before
                // make a toast to tell them what happened
            } else {
                // make a toast to tell them what happened
                Toast.makeText(this, "All the Fields are Required", Toast.LENGTH_LONG).show()
            }
        }

        // when we want to go back to the list
        binding.backFAB.setOnClickListener {
            startActivity(Intent(this, GridViewAdapter::class.java))
        }
    }
}