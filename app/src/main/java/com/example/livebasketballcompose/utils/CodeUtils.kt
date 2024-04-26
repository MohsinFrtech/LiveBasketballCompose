package com.example.livebasketballcompose.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.opengl.Visibility
import android.os.Build
import android.text.format.DateFormat
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object CodeUtils {


    //Function to check either device is emulator or not...
    fun checkRunningDeviceIsReal(): Boolean {
        try {
            val isProbablyRunningOnEmulator: Boolean by lazy {
                // Android SDK emulator
                return@lazy ((Build.FINGERPRINT.startsWith("google/sdk_gphone_")
                        && Build.FINGERPRINT.endsWith(":user/release-keys")
                        && Build.MANUFACTURER == "Google" && Build.PRODUCT.startsWith("sdk_gphone_") && Build.BRAND == "google"
                        && Build.MODEL.startsWith("sdk_gphone_"))
                        || Build.FINGERPRINT.startsWith("generic")
                        || Build.FINGERPRINT.startsWith("unknown")
                        || Build.MODEL.contains("google_sdk")
                        || Build.MODEL.contains("Emulator")
                        || Build.MODEL.contains("Android SDK built for x86")
                        //bluestacks
                        || "QC_Reference_Phone" == Build.BOARD && !"Xiaomi".equals(
                    Build.MANUFACTURER,
                    ignoreCase = true
                ) //bluestacks
                        || Build.MANUFACTURER.contains("Genymotion")
                        || Build.HOST.startsWith("Build") //MSI App Player
                        || Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")
                        || Build.PRODUCT == "google_sdk"
                        || Build.FINGERPRINT.contains("generic")
                        // another Android SDK emulator check
                        )
            }
            return isProbablyRunningOnEmulator
        } catch (e: Exception) {
            Log.d("Exception", "" + e.message)
            return false
        }

    }


    fun View.visibility(visibility: Boolean) {
        if (visibility) this.visibility = View.VISIBLE else this.visibility = View.GONE
    }

    //Function to check notification permission...
//    private fun isNotificationPermissionGranted(activity: Activity): Boolean {
//        when {
//            ContextCompat.checkSelfPermission(
//                activity,
//                Manifest.permission.POST_NOTIFICATIONS
//            ) == PackageManager.PERMISSION_GRANTED -> {
//                return true
//            }
//
//            shouldShowRequestPermissionRationale(activity, Manifest.permission.POST_NOTIFICATIONS)
//            -> {
//                //view visibility
//                return false
//            }
//
//            else -> {
//                return false
//            }
//
//        }
//    }

    ///Function to check either device is rooted or not.....
    fun checkDeviceRootedOrNot(activity: Activity): Boolean {
        return checkForSuFile() || checkForSuCommand() ||
                checkForSuperuserApk(activity) || checkForBusyBoxBinary() || checkForMagiskManager(
            activity
        )
    }

    private fun checkForSuCommand(): Boolean {
        return try {
            // check if the device is rooted
            val file = File("/system/app/Superuser.apk")
            if (file.exists()) {
                return true
            }
            val command: Array<String> = arrayOf("/system/xbin/which", "su")
            val process = Runtime.getRuntime().exec(command)
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            if (reader.readLine() != null) {
                return true
            }
            return false
        } catch (e: Exception) {
            false
        }
    }

    private fun checkForSuFile(): Boolean {
        val paths = arrayOf(
            "/system/app/Superuser.apk",
            "/sbin/su",
            "/system/bin/su",
            "/system/xbin/su",
            "/data/local/xbin/su",
            "/data/local/bin/su",
            "/system/sd/xbin/su",
            "/system/bin/failsafe/su",
            "/data/local/su"
        )
        for (path in paths) {
            if (File(path).exists()) {
                return true
            }
        }
        return false
    }

    private fun checkForSuperuserApk(activity: Activity?): Boolean {
        val packageName = "eu.chainfire.supersu"
        val packageManager = activity?.packageManager
        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                packageManager?.getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(0))
                true
            } else {
                packageManager?.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
                true
            }

        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    private fun checkForMagiskManager(activity: Activity?): Boolean {
        val packageName = "com.topjohnwu.magisk"
        val packageManager = activity?.packageManager
        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                packageManager?.getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(0))
                true
            } else {
                packageManager?.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
                true
            }

        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    private fun checkForBusyBoxBinary(): Boolean {
        val paths = arrayOf("/system/bin/busybox", "/system/xbin/busybox", "/sbin/busybox")
        try {
            for (path in paths) {
                val process = Runtime.getRuntime().exec(arrayOf("which", path))
                if (process.waitFor() == 0) {
                    return true
                }
            }
            return false
        } catch (e: Exception) {
            return false
        }
    }

    fun navigateToNextScreen(context: Context, clazz: Class<*>) {
        context.startActivity(Intent(context, clazz))
    }

    fun checkInternetIsAvailable(context: Context): Boolean {
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val network = connectivityManager?.activeNetwork
        val networkCapabilities = connectivityManager?.getNetworkCapabilities(network)
        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }

    //Function to check notification permission...
    fun isNotificationPermissionGranted(activity: Activity): Boolean {
        when {
            ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED -> {
                return true
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                activity,
                Manifest.permission.POST_NOTIFICATIONS
            )
            -> {
                //view visibility
                return false
            }

            else -> {
                return false
            }

        }
    }

    fun dateAndTime(channelDate: String?): String? {
        val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH)
        df.timeZone = TimeZone.getTimeZone("UTC")
        val date = channelDate?.let { df.parse(it) }
        df.timeZone = TimeZone.getDefault()
        val formattedDate = date?.let { df.format(it) }
        val date2: Date? = formattedDate?.let { df.parse(it) }
        val cal = Calendar.getInstance()
        if (date2 != null) {
            cal.time = date2
        }
        var hours = cal[Calendar.HOUR_OF_DAY]
        val minutes = cal[Calendar.MINUTE]
        var timeInAmOrPm = ""

        if (hours > 0) {
            timeInAmOrPm = if (hours >= 12) {
                "PM"
            } else {
                "AM"
            }
        }


        if (hours > 0) {
            if (hours >= 12) {
                if (hours == 12) {
                    /////
                } else {
                    hours -= 12
                }
            }
        }

        if (hours == 0) {
            hours = 12
            timeInAmOrPm = "AM"
        }

        val dayOfTheWeek =
            DateFormat.format("EEEE", date) as String

        val day = DateFormat.format("dd", date) as String

        val monthString =
            DateFormat.format("MMM", date) as String
        val year = DateFormat.format("yyyy", date) as String


        if (minutes < 9) {
            return "$dayOfTheWeek, $day $monthString, $year"
        } else {
            return "$dayOfTheWeek, $day $monthString, $year"

        }
    }

}