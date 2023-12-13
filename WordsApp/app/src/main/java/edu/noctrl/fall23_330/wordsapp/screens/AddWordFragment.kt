package edu.noctrl.fall23_330.wordsapp.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import edu.noctrl.fall23_330.wordsapp.R
import edu.noctrl.fall23_330.wordsapp.ViewModel
import edu.noctrl.fall23_330.wordsapp.ViewModelFactory
import edu.noctrl.fall23_330.wordsapp.WordsApp
import edu.noctrl.fall23_330.wordsapp.databinding.FragmentAddWordBinding
import edu.noctrl.fall23_330.wordsapp.databinding.FragmentStartingBinding
import edu.noctrl.fall23_330.wordsapp.databinding.FragmentWordSearchBinding

class AddWordFragment : Fragment() {
    private val TAG = javaClass.simpleName
    private var _binding: FragmentAddWordBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ViewModel by activityViewModels {
        ViewModelFactory((activity?.application as WordsApp).database.wordDao())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAddWordBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            Log.d(TAG, viewModel.word.value.toString())
        val wordNameDisplay = binding.wordName
        val def1Display = binding.wordDef
        val def2Display = binding.wordDef2
        val def3Display = binding.wordDef3
        val artId = binding.wordArt
        wordNameDisplay.text = viewModel.word.value?.word
        //def1Display.text = viewModel.word.value?.shortDef1
        //def2Display.text = viewModel.word.value?.shortDef2
        //def3Display.text = viewModel.word.value?.shortDef3
    }
}