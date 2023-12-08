package edu.noctrl.fall23_330.wordsapp

import android.app.Application
import edu.noctrl.fall23_330.wordsapp.database.WordDatabase

class WordsApp: Application() {
    val database: WordDatabase by lazy{ WordDatabase.getDatabase(this)}
}
