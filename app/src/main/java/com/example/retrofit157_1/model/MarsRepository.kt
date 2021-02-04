package com.example.retrofit157_1.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retrofit157_1.model.local.MarsDao
import com.example.retrofit157_1.model.pojo.MarsTerrain
import com.example.retrofit157_1.model.remote.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MarsRepository(private val dao: MarsDao) {

    private val services = RetrofitClient.retrofitInstance()
    val liveDataMarsTerrainDB = dao.getAllTerrainsDB()

    // Función que utiliza las coroutina para la conexión al servicio.
    suspend fun getTerrainWithCoroutines()  {
        Log.d("REPOSITORY", "UTILIZANDO COROUTINES")
        try {
            val response = RetrofitClient.retrofitInstance().fetchMarsTerrainCoroutines()
            when(response.isSuccessful) {
                true -> response.body()?.let {
                    //Aca se esta insertando en la Base de datos
                   dao.insertAllTerrains(it)
                }
                false -> Log.d("ERROR", " ${response.code()} : ${response.errorBody()} ")
            }
        } catch (t: Throwable){
            Log.e("ERROR CORUTINA", t.message.toString())
        }
    }

    fun getTerrainByID(id: String) : LiveData<MarsTerrain> {
        return dao.getTerrainByID(id)
    }

}