package com.example.livebasketballcompose.cppfiles


import com.example.livebasketballcompose.utils.AppConstants
import com.example.livebasketballcompose.utils.AppConstants.BASKETTOKEN
import com.example.livebasketballcompose.utils.AppConstants.BASKET_API_BASE_URL
import com.example.livebasketballcompose.utils.AppConstants.Streaming_API_BASE_URL
import com.example.livebasketballcompose.utils.AppConstants.appTaken
import com.example.livebasketballcompose.utils.AppConstants.numberValues
import com.example.livebasketballcompose.utils.AppConstants.userRepAlgo


class CppResult {
    private var fullSize = 0

    fun setUpValues(file1: Array<String?>?) {
        appTaken = file1?.get(10).toString()
        BASKET_API_BASE_URL = file1?.get(12).toString()
        Streaming_API_BASE_URL = file1?.get(11).toString()
        BASKETTOKEN = file1?.get(15).toString()
        numberValues= file1?.get(13).toString()
    }

    fun getTripleArrayFromNumbers(valueParams: String): Triple<Array<String>, Array<String>, Array<String>> {
        val myValue = valueParams
        val mainArray: Array<String> = myValue.toCharArray().map { it.toString() }.toTypedArray()
        val mainArraySize = mainArray.size
        fullSize = mainArraySize
        val array1 = mainArray.copyOfRange(0, (mainArraySize + 1) / 3)
        val arr2 = mainArray.copyOfRange((mainArraySize + 1) / 3, mainArraySize)
        val array2 = arr2.copyOfRange(0, (arr2.size + 1) / 2)
        val array3 = arr2.copyOfRange((arr2.size + 1) / 2, arr2.size)
        return Triple(array1, array2, array3)
    }

    fun returnValueOfSize(): Int {
        return fullSize
    }

     fun fileProcessing(strCon: String, sizeVal: Int, mainIndex: Array<String?>?) {
        var string1 = strCon
        val string2Pick = (sizeVal / 4)
        val char2Pick = (sizeVal * 0.7).toInt()

        if (string2Pick in 0..9) {
            val getFileNumberAt2nd2 = mainIndex
            val char1ToReplace =
                getFileNumberAt2nd2?.get(string2Pick)?.toCharArray()?.get(char2Pick)
            val rep = userRepAlgo.toRegex()
            string1 = rep.replace(string1, char1ToReplace.toString())
            AppConstants.myUserLock1 = string1
        }

    }
}