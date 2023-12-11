package edu.noctrl.fall23_330.wordsapp.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import edu.noctrl.fall23_330.wordsapp.R
import edu.noctrl.fall23_330.wordsapp.databinding.FragmentWordSearchBinding

class WordSearchFragment : Fragment() {
    private var _binding: FragmentWordSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentWordSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchButton = binding.searchButton
        val backButton = binding.backButton

        searchButton.setOnClickListener{
        }

        backButton.setOnClickListener{
            findNavController().navigate(R.id.startingFragment)
        }
    }
}