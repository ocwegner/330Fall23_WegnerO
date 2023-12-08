package edu.noctrl.fall23_330.wordsapp.network

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WordsDataClass(
    @PrimaryKey @ColumnInfo (name = "word") val word: String,
    @ColumnInfo (name = "shortdef1") val shortDef1: String,
    @ColumnInfo (name = "shortdef2") val shortDef2: String? = null,
    @ColumnInfo (name = "shortdef3") val shortDef3: String? = null,
    @ColumnInfo (name = "defcount") val shortDefCount: Int = 0,
    @ColumnInfo (name = "artid") val artId: String? = null,
    @ColumnInfo (name = "active") val activeStatus: Boolean = true
)