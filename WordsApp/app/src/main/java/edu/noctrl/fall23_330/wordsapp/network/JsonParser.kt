package edu.noctrl.fall23_330.wordsapp.network

import org.json.JSONArray

fun parseJsonStringToListOfWords(jsonStr: String): List<String>{
    val jsonArrayObj = JSONArray(jsonStr)
    val wordList = mutableListOf<String>()
    for(i in 0 until jsonArrayObj.length()){
        wordList.add(i, jsonArrayObj.getString(i))
    }

    return wordList
}

fun parseJsonToWord(word: String, jsonString: String) : WordsDataClass {
    val json = JSONArray(jsonString)
    // TODO populate a Word object with values from the jsonString
    val entry = json.getJSONObject(0)
    val shortDef = entry.getJSONArray("shortdef")
    var image: String? = null

    if (entry.has("art")){
        image = entry.getJSONObject("art").getString("artid")
    }

    val wordObject = when (shortDef.length()){
        0 -> WordsDataClass(word, "No definition available!")
        1 -> WordsDataClass(word, shortDef.getString(0), artId = image)
        2 -> WordsDataClass(word, shortDef.getString(0), shortDef.getString(1))
        else -> WordsDataClass(
            word, shortDef.getString(0),
            shortDef.getString(1),
            shortDef.getString(2),
            artId = image
        )
    }

    // TODO return the Word object
    return wordObject
}