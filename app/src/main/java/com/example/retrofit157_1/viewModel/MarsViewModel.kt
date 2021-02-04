package com.example.retrofit157_1.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit157_1.model.MarsRepository
import com.example.retrofit157_1.model.local.MarsDataBase
import com.example.retrofit157_1.model.pojo.MarsTerrain
import kotlinx.coroutines.launch

class MarsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MarsRepository
    val marsTerrainLiveDataFromDB: LiveData<List<MarsTerrain>>

    init {
        val dao = MarsDataBase.getDataBase(application).getMarsDao()
        repository = MarsRepository(dao)
         viewModelScope.launch {
            repository.getTerrainWithCoroutines()
        }
        marsTerrainLiveDataFromDB = repository.liveDataMarsTerrainDB
    }

    // Este seria para actualizar desde internet los datos.
    fun getFetchTerrainsWithCoroutines() = viewModelScope.launch {
        repository.getTerrainWithCoroutines()
    }

    fun getTerrainByID(id: String): LiveData<MarsTerrain> {
        return repository.getTerrainByID(id)
    }


}