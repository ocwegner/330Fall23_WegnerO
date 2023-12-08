package edu.noctrl.fall23_330.wordsapp.network

import org.junit.Assert
import org.junit.Test

class JsonParserTest {
    @Test
    fun parse_json_string_array_to_list_of_words(){
        val jsonString = javaClass.getResource("/string_array_response.json")?.readText()

        val wordList = parseJsonStringToListOfWords(jsonString!!)

        Assert.assertEquals(4, wordList.size)
    }
}