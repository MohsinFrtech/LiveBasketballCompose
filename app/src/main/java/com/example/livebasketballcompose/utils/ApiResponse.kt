package com.example.livebasketballcompose.utils

import com.example.livebasketballcompose.models.DataModel
import com.example.livebasketballcompose.utils.AppConstants.myUserCheck1
import com.example.livebasketballcompose.utils.AppConstants.passValue
import com.example.livebasketballcompose.utils.AppConstants.userBaseExtraDel2
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.json.JSONObject

class ApiResponse {

    fun parseResponse(data: String?): DataModel {
        val stringValue = data?.let { it1 -> StreamingUtils.saveResponse(it1, passValue) }
        var jobj: JSONObject? = JSONObject()
        jobj = stringValue?.let { JSONObject(it) }
        val gson: Gson = GsonBuilder()
            .setLenient()
            .create()
        val date = gson.fromJson(jobj.toString(), DataModel::class.java)
        return date
    }

    fun getExtraValuesFromResponse(date: DataModel?){
        if (date?.extra_1.toString().isNotEmpty()) {
            date?.extra_1 = StreamingUtils.decryptBase64(date?.extra_1)
            val encrypt = date?.extra_1.toString().trim()
            val yourArray: List<String> =
                encrypt.split(userBaseExtraDel2)
            myUserCheck1 = yourArray[0].trim()
        }
    }
}