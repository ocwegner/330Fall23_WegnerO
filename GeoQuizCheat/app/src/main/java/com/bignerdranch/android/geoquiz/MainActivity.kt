package com.bignerdranch.android.geoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.bignerdranch.android.geoquiz.databinding.ActivityMainBinding

private const val TAG = "MainActivity"
private const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"

class MainActivity : AppCompatActivity() {

    // TODO Challenge: Add a previous button
    // TODO 1: Add view binding ActivityMainBinding
    private lateinit var binding: ActivityMainBinding
    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true),
    )
    private var currentIndex = 0
    private var isCheater = false
    private val cheatLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result -> if ( result.resultCode == Activity.RESULT_OK){
            isCheater = result.data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            Log.d(TAG, "savedInstanceState is set.")
            currentIndex = savedInstanceState.getInt(CURRENT_INDEX_KEY, 0)
        }

        binding = ActivityMainBinding.inflate(this.layoutInflater)
        setContentView(binding.root)

        // TODO 2: Display the initial question
        binding.questionText.setText(questionBank[currentIndex].questionTextId)

        // TODO 3: Wire the Next button so that it advances to the next question
        binding.nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            binding.questionText.setText(questionBank[currentIndex].questionTextId)
        }

        // TODO 4: Modify this lambda function to check the answer and display an appropriate Toast
        binding.trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
        }

        // TODO 5: Modify this lambda function to check the answer and display an appropriate Toast
        binding.falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
        }

        binding.cheatButton.setOnClickListener {
            // Start the cheat activity
            val intent = Intent(this, CheatActivity::class.java)
            val answer = questionBank[currentIndex].answer
            intent.putExtra(EXTRA_ANSWER_KEY, answer)
            cheatLauncher.launch(intent)
        }
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        var resId = when {
            isCheater -> R.string.judgment_toast
            userAnswer == correctAnswer -> R.string.correct_toast
            else -> R.string.incorrect_toast
            }
        isCheater = false
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show()
    }

    // TODO 8: Use activity methods to save instance state to persist data across device
    // configuration changes.
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")
        outState.putInt(CURRENT_INDEX_KEY, this.currentIndex)
    }
}
