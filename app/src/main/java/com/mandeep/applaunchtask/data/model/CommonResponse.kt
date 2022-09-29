package com.mandeep.talkchargerassignment.data.model

import com.google.gson.JsonObject

/**
 * Developed By: Mandeep Singh
 * Dated On : Sat , 17 Sep 2022
 * Project : Talk Charger Assignment
 */
data class CommonResponse(
    val data: JsonObject?,
    val message: String,
    val statusCode: Int
)

