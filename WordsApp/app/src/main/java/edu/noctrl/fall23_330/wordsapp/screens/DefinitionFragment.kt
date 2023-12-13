package edu.noctrl.fall23_330.wordsapp.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import edu.noctrl.fall23_330.wordsapp.R
import edu.noctrl.fall23_330.wordsapp.ViewModel
import edu.noctrl.fall23_330.wordsapp.ViewModelFactory
import edu.noctrl.fall23_330.wordsapp.WordsApp
import edu.noctrl.fall23_330.wordsapp.databinding.FragmentWordSearchBinding

class DefinitionFragment : Fragment() {
    private var _binding: FragmentWordSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ViewModel by activityViewModels {
        ViewModelFactory((activity?.application as WordsApp).database.wordDao())
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val backButton = binding.backButton

        backButton.setOnClickListener{
            findNavController().navigate(R.id.startingFragment)
        }
    }
}