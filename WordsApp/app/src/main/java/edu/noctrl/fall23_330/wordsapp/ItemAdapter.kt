package edu.noctrl.fall23_330.wordsapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.noctrl.fall23_330.wordsapp.databinding.FragmentStartingBinding
import edu.noctrl.fall23_330.wordsapp.databinding.RecyclerViewBinding
import edu.noctrl.fall23_330.wordsapp.network.WordsDataClass

class ItemAdapter(private val clickListener: WordListener): ListAdapter<WordsDataClass, ItemAdapter.WordHolder>(DiffCallback){
   companion object {
       private val DiffCallback = object : DiffUtil.ItemCallback<WordsDataClass>(){
           override fun areItemsTheSame(oldItem: WordsDataClass, newItem: WordsDataClass): Boolean {
               return oldItem.word == newItem.word
           }

           override fun areContentsTheSame(oldItem: WordsDataClass, newItem: WordsDataClass): Boolean {
               return oldItem == newItem
           }
       }
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordHolder {
        return WordHolder(
            RecyclerViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WordHolder, position: Int) {
        val word = getItem(position)
        holder.bind(word)
    }

    class WordHolder (private var binding: RecyclerViewBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(word: WordsDataClass) {
            binding.wordColumn.text = word.word
        }
    }
}

class WordListener(val clickListener: (wordChosen: WordsDataClass) -> Unit){
    fun onClick(wordChosen: WordsDataClass) = clickListener(wordChosen)

    //TODO: look at amphibians to finish implementation
}