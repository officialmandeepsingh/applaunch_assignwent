package com.mandeep.applaunchtask.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.mandeep.applaunchtask.data.local.RoomResource
import com.mandeep.applaunchtask.data.local.Status
import com.mandeep.applaunchtask.data.local.User
import com.mandeep.applaunchtask.data.repository.LocalRepository
import com.mandeep.applaunchtask.util.ApiStatus
import com.mandeep.talkchargerassignment.data.remote.Resource
import com.mandeep.talkchargerassignment.data.remote.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(val localRepository: LocalRepository, val remoteRepository: WeatherRepository) : ViewModel() {

    private var _user: MutableLiveData<RoomResource<Any>> = MutableLiveData(RoomResource(Status.CLEAR,null))
    val user get() = _user
    private val _currentWeather = MutableLiveData<Resource<JsonObject>?>()
    val currentWeather = _currentWeather


    fun addUser(user: User) {
        viewModelScope.launch {
            localRepository.addUser(user).let {
                _user.postValue(RoomResource(Status.INSERT, it))
            }
        }
    }

    fun getAllUser() {
        viewModelScope.launch {
            localRepository.getAllUserList().let { userList ->
                _user.postValue(RoomResource(Status.RETRIVE_ALL, userList))
            }
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            localRepository.deleteUser(user).let {
                _user.postValue(RoomResource(Status.DELETE, null))
            }
        }
    }

    fun getCurrentWeather(map: HashMap<String, String>) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _currentWeather.postValue(Resource.loading(null))
                val response = remoteRepository.getWeather(map)
                Log.d("=>", "getCurrentWeather() called: ${response.body().toString()}")
                when (response.code()) {
                    ApiStatus.SUCCESS ->
                        _currentWeather.postValue(Resource.success(response.body()))
                    ApiStatus.ERROR ->
                        _currentWeather.postValue(Resource.error(response.errorBody()!!, null))
                    ApiStatus.UNAUTHORIZE ->
                        _currentWeather.postValue(
                            Resource.unAuthorized(
                                response.errorBody()!!,
                                null
                            )
                        )
                    ApiStatus.TIMEOUT ->
                        _currentWeather.postValue(Resource.timeOut(message = "Server not Respond"))

                }
            } catch (e: Exception) {

            }
        }
    }


}