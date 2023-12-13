package edu.noctrl.fall23_330.wordsapp.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import edu.noctrl.fall23_330.wordsapp.ItemAdapter
import edu.noctrl.fall23_330.wordsapp.R
import edu.noctrl.fall23_330.wordsapp.ViewModel
import edu.noctrl.fall23_330.wordsapp.ViewModelFactory
import edu.noctrl.fall23_330.wordsapp.WordListener
import edu.noctrl.fall23_330.wordsapp.WordsApp
import edu.noctrl.fall23_330.wordsapp.databinding.FragmentStartingBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue


class StartingFragment : Fragment(){
    private var _binding: FragmentStartingBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var wordUsed: TextView

    private val viewModel: ViewModel by activityViewModels {
        ViewModelFactory((activity?.application as WordsApp).database.wordDao())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentStartingBinding.inflate(inflater, container, false)

        return binding.root

        //TODO: figure out how to implement onClick stuff for when a word in the list is clicked
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.RecyclerView
        //i have no idea why this isn't working, I have been trying to fix this for an hour
        //i'm sure the solution is something simple, but I just have no brainpower anymore.
        wordUsed = recyclerView.findViewById(R.id.wordColumn)

        val itemAdapter = ItemAdapter(WordListener { wordChosen -> viewModel.onWordClicked(wordChosen)
            findNavController().navigate(R.id.action_startingFragment_to_definitionFragment)})
        recyclerView.adapter = itemAdapter
        lifecycle.coroutineScope.launch {
            viewModel.fullList().collect() {
                Log.d("Home Fragment", it.toString())
                itemAdapter.submitList(it)
            }
        }

        wordUsed.setOnClickListener{
            findNavController().navigate(R.id.definitionFragment)
        }

        val addButton = binding.addButtonMenu
        addButton.setOnClickListener{
            findNavController().navigate(R.id.wordSearchFragment)
        }
    }
}