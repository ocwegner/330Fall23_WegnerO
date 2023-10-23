package com.bignerdranch.android.geoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bignerdranch.android.geoquiz.databinding.ActivityCheatBinding
import com.bignerdranch.android.geoquiz.databinding.FragmentCheatBinding

private const val ARG_CORRECT_ANSWER = "correct_answer"
private const val TAG = "CheatFragment"

class CheatFragment : Fragment() {
    companion object {
        val CHEAT_RESULT_KEY = "cheatResultKey"
        val IS_CHEATER = "isCheater"
    }
    private var correctAnswer : Boolean = false

    private var _binding: FragmentCheatBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        answer = intent.getBooleanExtra(EXTRA_ANSWER_KEY, false)
        binding.showAnswerButton.setOnClickListener {
            val textVal = when {
                answer -> R.string.true_button
                else -> R.string.false_button
            }
            binding.answerTextView.setText(textVal)
            val intentData = Intent().apply {
                putExtra(EXTRA_ANSWER_SHOWN, true)
            }
            setResult(Activity.RESULT_OK, intentData)
        }
    }
}