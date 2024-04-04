package com.example.advweek4160420105.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.example.advweek4160420105.R
import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import com.example.advweek4160420105.util.createNotificationChannel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    init {
        instance = this
    }

    companion object {
        private var instance:MainActivity ?= null

        fun showNotification(title:String, content:String, icon:Int) {
            val channelId = "${instance?.packageName}-${instance?.getString(R.string.app_name)}"

            val notificationBuilder = NotificationCompat.Builder(instance!!.applicationContext, channelId).apply {
                setSmallIcon(icon)
                setContentTitle(title)
                setContentText(content)
                setStyle(NotificationCompat.BigTextStyle())
                priority = NotificationCompat.PRIORITY_DEFAULT
                setAutoCancel(true)
            }
            val notificationManager = NotificationManagerCompat.from(instance!!.applicationContext.applicationContext!!)
            if (ActivityCompat.checkSelfPermission(instance!!.applicationContext, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(instance!!,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),1)
                return
            }
            notificationManager.notify(1001, notificationBuilder.build())

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel(this,
            NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
            getString(R.string.app_name), "App notification channel.")
    }



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            1 -> {
                if(grantResults.isNotEmpty() && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {
                    Log.d("permission", "granted")
                    createNotificationChannel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT,
                        false, "Notif Title", "Notification channel for creating new student")
                } else {
                    Log.d("permission", "deny")
                }
            }
        }
    }
}