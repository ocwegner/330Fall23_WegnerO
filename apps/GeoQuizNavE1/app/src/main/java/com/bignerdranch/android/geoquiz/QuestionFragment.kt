package com.bignerdranch.android.geoquiz

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.geoquiz.databinding.FragmentQuestionBinding

private const val TAG = "QuestionFragment"
private const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"

class QuestionFragment : Fragment() {
    private var _binding: FragmentQuestionBinding? = null
    private val binding get() = _binding!!
    private var userScore = 0

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true),
        Question(R.string.question_amazon, true),
        Question(R.string.question_uk, false),
        Question(R.string.question_antarctica, true),
        Question(R.string.question_great_barrier_reef, false),
        Question(R.string.question_everest, true),
        Question(R.string.question_chile, false),
        Question(R.string.question_russia, true),
        Question(R.string.question_united_states, true),
        Question(R.string.question_pacific, true),
        Question(R.string.question_dead_sea, true),
        Question(R.string.question_france, false),
        Question(R.string.question_prime_meridian, true),
        Question(R.string.question_andes, true)
    )
    private var currentIndex = 0
    private var isCheater = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(CheatFragment.CHEAT_RESULT_KEY)
        { _, bundle -> isCheater = bundle.getBoolean(CheatFragment.IS_CHEATER) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentQuestionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        //disable true+false buttons after initial answer
        fun isAnswered(currentIndex: Int) {
            val isQuestionAnswered = questionBank[currentIndex].answered
            binding.trueButton.isEnabled = !isQuestionAnswered
            binding.falseButton.isEnabled = !isQuestionAnswered
        }

        if (savedInstanceState != null) {
            Log.d(TAG, "savedInstanceState is set.")
            currentIndex = savedInstanceState.getInt(CURRENT_INDEX_KEY, 0)
        }
        binding.questionText.setText(questionBank[currentIndex].testResId)

        //make a PREV button
        binding.previousButton.setOnClickListener {
            currentIndex = (currentIndex - 1) % questionBank.size
            if (currentIndex >= 0) {
                isAnswered(currentIndex)
                binding.questionText.setText(questionBank[currentIndex].testResId)
            }
            else {
                currentIndex = 0
                Toast.makeText(this.context, "Can't go back farther!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.questionText.setOnClickListener{
            currentIndex = (currentIndex + 1) % questionBank.size
            binding.questionText.setText(questionBank[currentIndex].testResId)
        }

        binding.trueButton.setOnClickListener {
            binding.trueButton.isEnabled = false
            binding.falseButton.isEnabled = false
            questionBank[currentIndex].answered = true
            checkAnswer(true)
            if (currentIndex == questionBank.size - 1){
                fun displayScore() {
                    val finalScoreNum = (userScore.toDouble() / questionBank.size.toDouble()) * 100
                    val finalScore = finalScoreNum.toString().format("%2f") + "%"
                    val stringForToast = userScore.toString().plus(" out of ").plus(questionBank.size.toString()).plus(" correct. ").plus(finalScore)
                    Toast.makeText(this.context, stringForToast, Toast.LENGTH_SHORT).show()
                }
                displayScore()
            }
        }

        binding.falseButton.setOnClickListener {
            binding.trueButton.isEnabled = false
            binding.falseButton.isEnabled = false
            questionBank[currentIndex].answered = true
            checkAnswer(false)
            if (currentIndex == questionBank.size - 1){
                fun displayScore() {
                    val finalScoreNum = (userScore.toDouble() / questionBank.size.toDouble()) * 100
                    val finalScore = "%.2f".format(finalScoreNum.toString()) + "%"
                    val stringForToast = userScore.toString().plus(" out of ").plus(questionBank.size.toString()).plus(" correct. ").plus(finalScore)
                    Toast.makeText(this.context, stringForToast, Toast.LENGTH_SHORT).show()
                }
                displayScore()
            }
        }

        binding.nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            isAnswered(currentIndex)
            binding.questionText.setText(questionBank[currentIndex].testResId)
        }

        binding.cheatButton.setOnClickListener {
            // Start the cheat activity
            val answer = questionBank[currentIndex].answer
            val action = QuestionFragmentDirections.actionQuestionFragmentToCheatFragment2(answer)
            this.findNavController().navigate(action)
        }
    }

    private fun displayScore() {
        val finalScoreNum = (userScore.toDouble() / questionBank.size.toDouble()) * 100
        val finalScore = finalScoreNum.toString().format("%2f") + "%"
        val stringForToast = userScore.toString().plus(" out of ").plus(questionBank.size.toString()).plus(" correct. ").plus(finalScore)
        Toast.makeText(this.context, stringForToast, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // configuration changes.
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")
        outState.putInt(CURRENT_INDEX_KEY, this.currentIndex)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val resId = when {
            isCheater -> R.string.judgment_toast
            userAnswer == correctAnswer -> R.string.correct_toast
            else -> R.string.incorrect_toast
        }
        isCheater = false

        if (correctAnswer == userAnswer){
            userScore += 1
        }

        Toast.makeText(this.context, resId, Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }
}
