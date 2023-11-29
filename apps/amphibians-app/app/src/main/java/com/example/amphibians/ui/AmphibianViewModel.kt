/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.amphibians.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amphibians.network.Amphibian
import com.example.amphibians.network.AmphibianApi
import com.example.amphibians.network.AmphibianApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

enum class AmphibianApiStatus {LOADING, ERROR, DONE}

class AmphibianViewModel : ViewModel() {

    // TODO: Create properties to represent MutableLiveData and LiveData for the API status
    private val _status = MutableLiveData<AmphibianApiStatus>(AmphibianApiStatus.LOADING)
    val status: LiveData<AmphibianApiStatus>
        get() = _status

    // TODO: Create properties to represent MutableLiveData and LiveData for a list of amphibian objects
    private val _amphibians = MutableLiveData<List<Amphibian>>()
    val amphibians: LiveData<List<Amphibian>>
        get() = _amphibians

    // TODO: Create properties to represent MutableLiveData and LiveData for a single amphibian object.
    //  This will be used to display the details of an amphibian when a list item is clicked
    private val _amphibian = MutableLiveData<Amphibian>()
    val amphibian: LiveData<Amphibian>
        get() = _amphibian

    // TODO: Create a function that gets a list of amphibians from the api service and sets the
    //  status via a Coroutine
    private fun getAmphibians(){
        viewModelScope.launch(Dispatchers.IO){
            try {
                _status.postValue(AmphibianApiStatus.DONE)
                _amphibians.postValue(AmphibianApi.retrofitService.getAmphibians())
            }
            catch(e: IOException){
                _status.postValue(AmphibianApiStatus.ERROR)
            }
        }
    }

    fun onAmphibianClicked(amphibian: Amphibian) {
        // TODO: Set the amphibian object
        _amphibian.value = amphibian
    }
    init {
        getAmphibians()
    }
}