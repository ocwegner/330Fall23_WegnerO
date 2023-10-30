package com.bignerdranch.android.geoquiz

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.bignerdranch.android.geoquiz.databinding.FragmentCheatBinding
private const val ARG_CORRECT_ANSWER = "correct_answer"
private const val TAG = "CheatFragment"
class CheatFragment : Fragment() {
    companion object {
        const val CHEAT_RESULT_KEY = "cheatResultKey"
        const val IS_CHEATER = "isCheater"
    }
    private var correctAnswer : Boolean = false

    private var _binding: FragmentCheatBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        Log.d(TAG, "arguments in onCreate: $arguments")
        arguments?.let{
            if(it.containsKey("correct_answer")){
                correctAnswer = it.getBoolean("correct_answer")
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        _binding = FragmentCheatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.showAnswerButton.setOnClickListener{
            val textVal = when{
                correctAnswer -> R.string.true_button
                else -> R.string.false_button
            }
            binding.answerTextView.setText(textVal)

            setFragmentResult(CHEAT_RESULT_KEY, bundleOf(IS_CHEATER to true))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}