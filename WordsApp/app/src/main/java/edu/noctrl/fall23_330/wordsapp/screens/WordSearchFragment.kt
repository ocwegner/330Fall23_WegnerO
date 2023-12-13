package edu.noctrl.fall23_330.wordsapp.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import edu.noctrl.fall23_330.wordsapp.ItemAdapter
import edu.noctrl.fall23_330.wordsapp.R
import edu.noctrl.fall23_330.wordsapp.SearchAdapter
import edu.noctrl.fall23_330.wordsapp.ViewModel
import edu.noctrl.fall23_330.wordsapp.ViewModelFactory
import edu.noctrl.fall23_330.wordsapp.WordListener
import edu.noctrl.fall23_330.wordsapp.WordsApp
import edu.noctrl.fall23_330.wordsapp.databinding.FragmentWordSearchBinding

class WordSearchFragment : Fragment() {
    private val TAG = javaClass.simpleName
    private var _binding: FragmentWordSearchBinding? = null
    private val binding get() = _binding!!
    //private val recyclerView = R.id.RecyclerView
    private val viewModel: ViewModel by activityViewModels {
        ViewModelFactory((activity?.application as WordsApp).database.wordDao())
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentWordSearchBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchButton = binding.searchButton
        val backButton = binding.backButton

        binding.wordSearchList.adapter = ItemAdapter(WordListener { wordChosen -> viewModel.onWordClicked(wordChosen)
            findNavController().navigate(R.id.action_wordSearchFragment_to_addWordFragment)
        })

        searchButton.setOnClickListener{
            wordSearch()
        }

        backButton.setOnClickListener{
            findNavController().navigate(R.id.startingFragment)
        }

        viewModel.word.observe(viewLifecycleOwner) { word ->
            if (null != word) {
                findNavController().navigate(R.id.action_wordSearchFragment_to_addWordFragment)
            }

            //i forgot to ask about the flags during our meeting, that's my fault
            /*if (viewModel.possibleWords.value == Screen.PARTIAL_MATCH) {
                (recyclerView.adapter as SearchAdapter).possibleWords = viewModel.possibleWords.value!!
                recyclerView.setHasFixedSize(true)
                recyclerView.visibility = View.VISIBLE
            }*/
        }
    }

    private fun wordSearch(){
        var searchedWord: EditText = binding.searchBar
        var searchWord = searchedWord.text.toString().lowercase()
        viewModel.performWordSearch(searchWord)
    }
}