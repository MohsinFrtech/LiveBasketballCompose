package com.example.livebasketballcompose.utils

import android.util.Base64
import com.example.livebasketballcompose.utils.AppConstants.algoName
import com.example.livebasketballcompose.utils.AppConstants.algoTypeS1
import com.example.livebasketballcompose.utils.AppConstants.algoTypeS2
import com.example.livebasketballcompose.utils.AppConstants.asp
import com.example.livebasketballcompose.utils.AppConstants.chName
import com.example.livebasketballcompose.utils.AppConstants.instanceVal
import com.example.livebasketballcompose.utils.AppConstants.networkIp
import com.example.livebasketballcompose.utils.AppConstants.parsedString
import com.example.livebasketballcompose.utils.AppConstants.transForm
import com.example.livebasketballcompose.utils.AppConstants.userBase
import com.example.livebasketballcompose.utils.AppConstants.userBaseDel
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.spec.KeySpec
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec
import kotlin.experimental.and

object StreamingUtils {

    fun saveResponse(encrypted: String, pwd: String): String? {
        try {
            val parts = encrypted.split("--").toTypedArray()
            if (parts.size != 3) return null
            val encryptedData = Base64.decode(parts[0], Base64.DEFAULT)
            val iv = Base64.decode(parts[1], Base64.DEFAULT)
            val salt = Base64.decode(parts[2], Base64.DEFAULT)
            val factory: SecretKeyFactory = SecretKeyFactory.getInstance(instanceVal)
            val spec: KeySpec = PBEKeySpec(pwd.toCharArray(), salt, 1024, 128)
            val tmp: SecretKey = factory.generateSecret(spec)
            val aesKey: SecretKey = SecretKeySpec(tmp.encoded, asp)
            val cipher: Cipher = Cipher.getInstance(transForm)
            cipher.init(Cipher.DECRYPT_MODE, aesKey, IvParameterSpec(iv))
            val result: ByteArray = cipher.doFinal(encryptedData)
            return String(result, charset(chName))
        } catch (e: Exception) {
            return ""
        }

    }
    fun decryptBase64(vale:String?): String{
        // Receiving side
        val data: ByteArray = Base64.decode(vale, Base64.DEFAULT)
        return String(data, StandardCharsets.UTF_8)
    }
    fun improveDeprecatedCode(link: String): String {
        val separated: List<String> =
            link.split(userBaseDel)
        val streamName = separated[separated.size - 2]
        val startTime = System.currentTimeMillis() / 1000
        val endTime = startTime + 77
        var sha1 = startTime.toString() + streamName + networkIp + parsedString + endTime
        sha1 = sHA1(sha1)
        var userval = ""
        userval = "$userBase$sha1-$endTime-$startTime"
        userval="$userBase${sHA1(streamName + parsedString + startTime.toString() + networkIp)}-$endTime-$startTime"
        return "$userBase${sHA2(streamName + parsedString + startTime.toString() + networkIp)}-$endTime-$startTime"
    }
    fun sHA2(text: String): String {
        val md = MessageDigest.getInstance(algoTypeS2)
        val textBytes = text.toByteArray(charset(algoName))
        md.update(textBytes, 0, textBytes.size)
        val sha1hash = md.digest()
        return convertToHex(sha1hash)
    }
    private fun convertToHex(data: ByteArray): String {
        val buf = StringBuilder()
        for (b in data) {
//                val v = b.toInt() and 0x0F
            var halfbyte: Int = (b.toInt().ushr(4)) and 0x0F
            var twohalfs = 0
            do {
                buf.append(
                    if (halfbyte in 0..9) {
                        ('0'.code + halfbyte).toChar()
                    } else {
                        ('a'.code + (halfbyte - 10)).toChar()
                    }
                )
                halfbyte = (b and 0x0F).toInt()
            } while (twohalfs++ < 1)
        }
        return buf.toString()
    }
    fun sHA1(text: String): String {
        val md = MessageDigest.getInstance(algoTypeS1)
        val textBytes = text.toByteArray(charset(algoName))
        md.update(textBytes, 0, textBytes.size)
        val sha1hash = md.digest()
        return convertToHex(sha1hash)
    }

}