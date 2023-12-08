package edu.noctrl.fall23_330.wordsapp.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.noctrl.fall23_330.wordsapp.R
import edu.noctrl.fall23_330.wordsapp.databinding.FragmentWordSearchBinding

class WordSearchFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentWordSearchBinding.inflate(inflater)


        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.

    }
}