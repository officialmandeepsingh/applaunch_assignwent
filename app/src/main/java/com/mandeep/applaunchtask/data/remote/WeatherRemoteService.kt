package com.mandeep.talkchargerassignment.data.remote

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface WeatherRemoteService {

    @GET(ApiPath.WEATHER_DETAILS)
    suspend fun getCurrentWeather(
        @QueryMap map: HashMap<String, String>
    ): Response<JsonObject>

}