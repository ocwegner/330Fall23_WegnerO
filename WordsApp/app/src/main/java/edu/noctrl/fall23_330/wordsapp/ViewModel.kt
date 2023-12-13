package edu.noctrl.fall23_330.wordsapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import edu.noctrl.fall23_330.wordsapp.database.DictionaryDao
import edu.noctrl.fall23_330.wordsapp.network.DictionaryApi.retrofitService
import edu.noctrl.fall23_330.wordsapp.network.WordsDataClass
import edu.noctrl.fall23_330.wordsapp.network.parseJsonStringToListOfWords
import edu.noctrl.fall23_330.wordsapp.network.parseJsonToWord
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class ViewModel(private val dictionaryDao: DictionaryDao): ViewModel() {
    private val TAG = javaClass.simpleName
    fun fullList(): Flow<List<WordsDataClass>> = dictionaryDao.getAll()
    private val _word = MutableLiveData<WordsDataClass>()
    private val _possibleWords = MutableLiveData<List<String>>()
    val word: LiveData<WordsDataClass>
        get() = _word
    val possibleWords: LiveData<List<String>>
        get() = _possibleWords
    fun onWordClicked(word: WordsDataClass) {
        // TODO: Create properties to represent MutableLiveData and LiveData for a single word object.
        ///  This will be used to display the details of a word when a list item is clicked or an
        //  exact match is found when searching for a word
        //TODO: look at amphibians app for implementation
        _word.value = word
    }

    fun performWordSearch(searchWord: String) {
        viewModelScope.launch { try {
            val response = retrofitService.getWord(searchWord)
            parseResponse(response, searchWord)
            }
            catch (e: Exception){
                Log.e("ViewModel", e.toString(), e)
            }
        }
    }

    private fun parseResponse(response: Response<String>, searchWord: String){
        if (response.isSuccessful){
            val jsonString = response.body().toString()
            Log.d(TAG, jsonString)
            if (jsonString.startsWith("[{")){
                Log.d(TAG, "parseJsonToWord")
                _word.value = parseJsonToWord(searchWord, jsonString)
            }
            else if (jsonString.startsWith("[\"")){
                Log.d(TAG, "parseJsonToStringList $jsonString")
                var parsedWords = parseJsonStringToListOfWords(jsonString)
                if (parsedWords.isEmpty()){
                    parsedWords = parsedWords.toMutableList()
                    parsedWords.add("No matching words found!")
                }
                _possibleWords.value = parsedWords
            }
            else if (jsonString.startsWith("[]")){
                _possibleWords.value = listOf("No matching words found!")
            }
            else{
                _possibleWords.value = listOf(response.body().toString())
            }
        }
        else{
            _possibleWords.value = listOf(response.errorBody().toString())
        }
    }
}
class ViewModelFactory(private val dictionaryDao: DictionaryDao): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(edu.noctrl.fall23_330.wordsapp.ViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return edu.noctrl.fall23_330.wordsapp.ViewModel(dictionaryDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

