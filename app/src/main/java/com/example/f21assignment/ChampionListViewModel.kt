package com.example.f21assignment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase

// : means extends
class ChampionListViewModel : ViewModel(){

    // this will hold a mutable list of Champion objects
    // instance variable that will hold mutable live data
    private val champions = MutableLiveData<List<Champion>>()

    // init will run when we create an instance of the ViewModel
    init {
        loadChampions()
    }

    // this method will load the Champion objects from Firebase.Firestore
    // https://firebase.google.com/docs/firestore/query-data/listen#kotlin+ktx
    private fun loadChampions(){

        //connecting to the database to the collection of champions
        val db = FirebaseFirestore.getInstance().collection("champions")
            //ordering by name and ascending
            .orderBy("name", Query.Direction.ASCENDING)
        
        //creates continuous listener on Firestore changes
        db.addSnapshotListener { documents , exception ->
            //if there is an exception
            exception?.let {
                // log the exception
                Log.i("DB Message", "Listen failed : " + exception)
                // get out of here
                return@addSnapshotListener
            }
            // if documents are null dont run .size()
            Log.i("DB Message", "# of elements returned: ${documents?.size()}")

            //creating an array list of Champion objects that will be used to populate the MutableLiveDate variable called champions
            val championList = ArrayList<Champion>()

            //loop over the documents from the DB and create Champion objects
            // if documents != null
            documents?.let{
                // for every document in the documents Database
                for (document in documents){
                    try {
                        // Post values into the correct fields (they need to have the correct names and data types as the champion class) or it will blow up
                        val champion = document.toObject(Champion::class.java)
                        // adding the champion to the array list
                        championList.add(champion)
                    } catch(e : Exception) {
                        //logging the exception (what document it happened at) into logcat
                        Log.i("DB Messages", document.toString())
                    }
                }
            }

            //after looping over all the documents and adding them to the array list
            // add the array list to the MutableLiveData object
            champions.value = championList
        }
    }

    // need a public facing method to give out the list of champions
    // function that is of type live data ( it will return live data that holds a champion )
        // live data allows us to Listen for changes
    fun getChampions() : LiveData<List<Champion>> {
        return champions;
    }
}