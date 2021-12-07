package com.GC200431861M.f21assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.GC200431861M.f21assignment.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val authDB = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //ensure we have an authenticated user
        if(authDB.currentUser == null){
            //if the users is not authenticated make log out user
            logout()
        }

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

//        // when we want to go back to the list
//        binding.backFAB.setOnClickListener {
//            // Resetting the interface
//            // clearing the fields so the users can add more data
//            binding.championNameEditText.setText("")
//            binding.championPassiveAbilityEditText.setText("")
//            binding.championAbilityOneEditText.setText("")
//            binding.championAbilityTwoEditText.setText("")
//            binding.championAbilityThreeEditText.setText("")
//            binding.championAbilityFourEditText.setText("")
//            // Going back to the list(recycler)
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
                //DISABLED!: we don't want to load the page again
                //startActivity(Intent(applicationContext, MainActivity::class.java))
                return true
            }
            R.id.championList -> {
                startActivity(Intent(applicationContext, GridRecyclerViewActivity::class.java))
                return true
            }
        }
        return  super.onOptionsItemSelected(item)
    }

    /**
     * Logout the user and send them to the sign in activity to sign in
     */
    private fun logout() {
        authDB.signOut()
        finish()
        startActivity(Intent(this, SignInActivity::class.java))
    }
}