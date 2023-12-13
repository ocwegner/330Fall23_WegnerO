package edu.noctrl.fall23_330.wordsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SearchAdapter(private val onClickListener: OnClickListener) : RecyclerView.Adapter<SearchAdapter.WordHolder>() {
    var possibleWords: List<String> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.possible_word_item, parent, false)
        return WordHolder(adapterLayout)
    }

    override fun getItemCount(): Int {
        return possibleWords.size
    }

    override fun onBindViewHolder(holder: WordHolder, position: Int) {
        holder.textView.text = possibleWords[position]
        holder.itemView.setOnClickListener{
            onClickListener.onClick(possibleWords[position])
        }
    }

    class WordHolder(private var view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.possibleWordItem)
    }

    class OnClickListener(val clickListener: (word: String) -> Unit){
        fun onClick(word: String) = clickListener(word)
    }
}