package com.example.f21assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.f21assignment.databinding.ActivityRecyclerListBinding

class RecyclerListActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRecyclerListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}