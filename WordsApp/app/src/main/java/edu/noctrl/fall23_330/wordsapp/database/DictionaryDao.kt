package edu.noctrl.fall23_330.wordsapp.database

import android.provider.UserDictionary.Words
import androidx.room.Dao
import androidx.room.Query
import edu.noctrl.fall23_330.wordsapp.network.WordsDataClass
import kotlinx.coroutines.flow.Flow

@Dao
interface DictionaryDao{
    @Query("SELECT * FROM wordsdataclass ORDER BY word ASC")
    fun getAll(): Flow<List<WordsDataClass>>

    @Query("SELECT * FROM wordsdataclass WHERE word = :word ORDER BY word ASC")
    fun getByWord(word: String): Flow<List<WordsDataClass>>

    @Query("SELECT * FROM wordsdataclass WHERE shortdef1 = :shortDef1 ORDER BY word ASC")
    fun getByDef1(shortDef1: String): Flow<List<WordsDataClass>>

    @Query("SELECT * FROM wordsdataclass WHERE shortdef2 = :shortDef2 ORDER BY word ASC")
    fun getByDef2(shortDef2: String): Flow<List<WordsDataClass>>

    @Query("SELECT * FROM wordsdataclass WHERE shortdef3 = :shortDef3 ORDER BY word ASC")
    fun getByDef3(shortDef3: String): Flow<List<WordsDataClass>>

    @Query("SELECT * FROM wordsdataclass WHERE defcount = :shortDefCount ORDER BY word ASC")
    fun getByCount(shortDefCount: String): Flow<List<WordsDataClass>>

    @Query("SELECT * FROM wordsdataclass WHERE artid = :artId ORDER BY word ASC")
    fun getByImg(artId: String): Flow<List<WordsDataClass>>

    @Query("SELECT * FROM wordsdataclass WHERE active = :activeStatus ORDER BY word ASC")
    fun getByActivity(activeStatus: String): Flow<List<WordsDataClass>>
}