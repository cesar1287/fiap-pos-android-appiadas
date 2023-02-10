package com.github.cesar1287.appiadas

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.cesar1287.appiadas.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var lastRandomNumber = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btNewJoke.setOnClickListener {
            tellJoke()
        }
    }

    private fun tellJoke() {
        val jokes = resources.getStringArray(R.array.jokers)

        val randomNumber = Random.nextInt(jokes.size)

        if (lastRandomNumber != randomNumber) {
            lastRandomNumber = randomNumber

            val joke = jokes[randomNumber]
            binding.tvJoke.text = joke
            playSong()
        } else {
            tellJoke()
        }
    }

    private fun playSong() {
        val mediaPlayer = MediaPlayer.create(this, R.raw.badumtss)
        mediaPlayer.start()
    }
}