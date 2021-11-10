package com.example.f21assignment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GridViewAdapter (val context : Context, val champions : List<Champion>, val itemListener: ChampionItemListener) : RecyclerView.Adapter<GridViewAdapter.ChampionViewHolder>(){

    /**
     * This class is used to allow us to access the item_champion.xml objects
     */
    inner class ChampionViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val nameTextView = itemView.findViewById<TextView>(R.id.nameTextView)
    }

    /**
     * This connects (aka inflates) the individual ViewHolder (which is the link to the item_restaurant.xml)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_grid_champion, parent, false)
        return ChampionViewHolder(view)
    }

    /**
     * This method will bind the viewHolder with a specific restaurant object
     */
    override fun onBindViewHolder(holder: ChampionViewHolder, position: Int) {
        val champion = champions[position]
        with (holder) {
            nameTextView.text = champion.name
            
            //if someone clicks the item
            itemView.setOnClickListener{
                //call the method and pass in that champion
                itemListener.championSelected(champion)
            }
        }
    }

    override fun getItemCount(): Int {
        return champions.size
    }

    interface ChampionItemListener {
        fun championSelected(champion : Champion)
    }




}