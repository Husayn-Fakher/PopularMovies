package com.example.popularmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.popularmovies.databinding.ActivityDetailsBinding


class DetailsActivity : AppCompatActivity() {

    companion object {
        const val OverView = "overview"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val overview = intent?.extras?.getString(OverView).toString()

        val textView = binding.textView

        textView.text = overview




    }
}