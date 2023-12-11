package edu.noctrl.fall23_330.wordsapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.noctrl.fall23_330.wordsapp.database.DictionaryDao
import edu.noctrl.fall23_330.wordsapp.network.WordsDataClass
import kotlinx.coroutines.flow.Flow

class ViewModel(private val dictionaryDao: DictionaryDao): ViewModel(){
    fun fullList(): Flow<List<WordsDataClass>> = dictionaryDao.getAll()
    fun onWordClicked(){
        //TODO: look at amphibians app for implementation
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