package com.mandeep.talkchargerassignment.data.remote

import com.google.gson.JsonObject
import retrofit2.Response
import javax.inject.Inject

class WeatherRepository @Inject constructor(val api: WeatherRemoteService) {

    suspend fun getWeather(map: HashMap<String, String>): Response<JsonObject> =
        api.getCurrentWeather(map)

}