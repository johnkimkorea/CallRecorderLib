package com.evgeniykim.callrecorderlib

import android.accessibilityservice.AccessibilityService
import android.graphics.PixelFormat
import android.media.AudioManager
import android.media.MediaRecorder
import android.os.Environment
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.accessibility.AccessibilityEvent
import android.widget.Button
import android.widget.FrameLayout
import com.aykuttasil.callrecord.CallRecord
import java.io.File
import java.io.IOException
import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*

class MyAccessibilityService : AccessibilityService() {

    private var TAG = "MyAccessibilityService"
    var mLayout: FrameLayout? = null

    var recorder: CallRecord? = null


    override fun onAccessibilityEvent(p0: AccessibilityEvent?) {

    }

    override fun onInterrupt() {

    }

    override fun onServiceConnected() {
        val wm: WindowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        mLayout = FrameLayout(this)
        val lp: WindowManager.LayoutParams = WindowManager.LayoutParams()
        lp.type = WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY
        lp.format = PixelFormat.TRANSLUCENT
        lp.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        lp.gravity = Gravity.TOP
        val inflater: LayoutInflater = LayoutInflater.from(this)
        inflater.inflate(R.layout.action_bar, mLayout)
        wm.addView(mLayout, lp)

        startRecording()
        stopRecording()
    }



    private fun startRecording() {
        val startRecordingBtn = mLayout?.findViewById<Button>(R.id.startRecordingBtn)
        startRecordingBtn?.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View?) {

                val sampleDir = File(getExternalFilesDir(null), "/TestRecording")
                if (!sampleDir.exists()) {
                    sampleDir.mkdirs()
                }

                recorder = CallRecord.Builder(this@MyAccessibilityService)
                    .setRecordFileName("records")
                    .setRecordDirName("Calls")
                    .setRecordDirPath(sampleDir.absolutePath)
                    .setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
                    .setOutputFormat(MediaRecorder.OutputFormat.AMR_NB)
                    .setAudioSource(MediaRecorder.AudioSource.VOICE_RECOGNITION)
                    .setShowSeed(true)
                    .setLogEnable(true)
                    .setShowPhoneNumber(true)
                    .build()

                recorder!!.startCallRecordService()
                Log.i(TAG, "Recording started ${sampleDir.absolutePath}")

            }

        })
    }

    private fun stopRecording() {

        var buttonStop = mLayout?.findViewById<Button>(R.id.stopRecordingBtn)
        buttonStop?.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View?) {

                recorder!!.stopCallReceiver()
                Log.i(TAG, "Recording stopped")

            }
        })




    }
}