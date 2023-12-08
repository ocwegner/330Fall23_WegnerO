package edu.noctrl.fall23_330.wordsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import edu.noctrl.fall23_330.wordsapp.WordsApp
import edu.noctrl.fall23_330.wordsapp.network.WordsDataClass

@Database(entities = [WordsDataClass::class], version = 1, exportSchema = false)
abstract class WordDatabase : RoomDatabase() {
    abstract fun wordDao(): DictionaryDao

    companion object {
        @Volatile
        private var INSTANCE: WordDatabase? = null
        fun getDatabase(context: Context): WordDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context, WordDatabase::class.java, "word_db")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}