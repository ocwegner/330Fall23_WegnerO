package edu.noctrl.fall23_330.wordsapp.network

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://www.dictionaryapi.com/api/v3/references/collegiate/json/"
private const val API_KEY = "50b5b538-8dad-4f69-8b58-6333d47f58ee"

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()


interface DictionaryApiService {
    @GET("{word}?key=${API_KEY}")
    suspend fun getWord(@Path("word") type: String): Response<String>
}

object DictionaryApi{
    val retrofitService: DictionaryApiService by lazy {retrofit.create(DictionaryApiService::class.java)}
}