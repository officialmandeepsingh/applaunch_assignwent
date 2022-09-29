package com.mandeep.applaunchtask.util

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import com.mandeep.talkchargerassignment.data.model.CommonResponse
import java.io.Reader

object CommonPojoUtils {
    inline fun <reified T : Any> getResponse(data: JsonObject?): T {
        return Gson().fromJson(data, T::class.java)
    }

    fun <T> getObjectList(jsonString: String?, cls: Class<T>?): List<T>? {
        val list: MutableList<T> = ArrayList()
        try {
            val gson = Gson()
            val array = JsonParser.parseString(jsonString).asJsonArray
            for (jsonElement in array) {
                list.add(gson.fromJson(jsonElement, cls))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }

    fun getResponse(charStream: Reader): CommonResponse {
        val type = object : TypeToken<CommonResponse>() {}.type
        return Gson().fromJson(charStream, type)
    }
}