package com.github.cesar1287.appiadas

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.view.isVisible
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
            binding.tvJokeAnswer.isVisible = false
            tellJoke()
        }
    }

    private fun tellJoke() {
        val jokesQuestions = resources.getStringArray(R.array.jokers_questions)
        val jokesAnswers = resources.getStringArray(R.array.jokers_answers)

        val randomNumber = Random.nextInt(jokesQuestions.size)

        if (lastRandomNumber != randomNumber) {
            lastRandomNumber = randomNumber

            val joke = jokesQuestions[randomNumber]
            binding.tvJokeQuestion.text = joke
            Handler(Looper.getMainLooper())
                .postDelayed(
                    {
                        binding.tvJokeAnswer.text = jokesAnswers[randomNumber]
                        binding.tvJokeAnswer.isVisible = true
                        playSong()
                    }, 3000L)
        } else {
            tellJoke()
        }
    }

    private fun playSong() {
        val mediaPlayer = MediaPlayer.create(this, R.raw.badumtss)
        mediaPlayer.start()
    }
}