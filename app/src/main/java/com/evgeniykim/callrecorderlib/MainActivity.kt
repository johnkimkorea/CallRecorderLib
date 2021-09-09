package com.evgeniykim.callrecorderlib

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val PERMISSIONS_REQUEST = 100

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkPermission()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun checkPermission() {
        val permission1 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG)
        val permission2 = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
        val permission3 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
        val permission4 = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
        val permission5 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
//        val permission6 = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
//        val permission7 = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE)
        val permission8 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val permission9 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
//        val permission10 = ContextCompat.checkSelfPermission(this, Manifest.permission.FOREGROUND_SERVICE)
        val permission11 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS)
//        val permission12 = ContextCompat.checkSelfPermission(this, Manifest.permission.SYSTEM_ALERT_WINDOW)
//        val permission13 = ContextCompat.checkSelfPermission(this, Manifest.permission.MODIFY_AUDIO_SETTINGS)
//        val permission14 = ContextCompat.checkSelfPermission(this, Manifest.permission.MANAGE_OWN_CALLS)
//        val permission15 = ContextCompat.checkSelfPermission(this, Manifest.permission.WAKE_LOCK)


        if (permission1 != PackageManager.PERMISSION_GRANTED &&
            permission2 != PackageManager.PERMISSION_GRANTED &&
            permission3 != PackageManager.PERMISSION_GRANTED &&
            permission4 != PackageManager.PERMISSION_GRANTED &&
            permission5 != PackageManager.PERMISSION_GRANTED &&
//            permission6 != PackageManager.PERMISSION_GRANTED &&
//            permission7 != PackageManager.PERMISSION_GRANTED &&
            permission8 != PackageManager.PERMISSION_GRANTED &&
            permission9 != PackageManager.PERMISSION_GRANTED &&
//            permission10 != PackageManager.PERMISSION_GRANTED &&
            permission11 != PackageManager.PERMISSION_GRANTED
//            permission12 != PackageManager.PERMISSION_GRANTED &&
//            permission13 != PackageManager.PERMISSION_GRANTED &&
//            permission14 != PackageManager.PERMISSION_GRANTED &&
//            permission15 != PackageManager.PERMISSION_GRANTED
        ) {

            requestPermission()

        } else {
            Toast.makeText(this, "Need permissions", Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun requestPermission() {
        ActivityCompat.requestPermissions(this,
            arrayOf(
                Manifest.permission.READ_CALL_LOG,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_PHONE_STATE,
//                Manifest.permission.INTERNET,
//                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
//                Manifest.permission.FOREGROUND_SERVICE,
                Manifest.permission.WRITE_CONTACTS
//                Manifest.permission.SYSTEM_ALERT_WINDOW,
//                Manifest.permission.MODIFY_AUDIO_SETTINGS,
//                Manifest.permission.MANAGE_OWN_CALLS,
//                Manifest.permission.WAKE_LOCK
            ),
            PERMISSIONS_REQUEST)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            PERMISSIONS_REQUEST -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    requestPermission()
                } else {
                    Toast.makeText(this, "yuou have permissions", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }
}