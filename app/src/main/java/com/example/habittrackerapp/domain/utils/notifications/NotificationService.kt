package com.example.habittrackerapp.domain.utils.notifications

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.job.JobInfo
import android.content.Context
import android.content.Intent
import android.os.Build
import java.util.*
import android.app.job.JobParameters
import android.app.job.JobScheduler
import android.app.job.JobService
import android.content.ComponentName
import android.os.Handler
import android.os.Looper
import android.util.Log

class NotificationService : JobService() {

    companion object {
        private const val JOB_ID = 1000
        private const val CHECK_INTERVAL = 1000

        private val handler = Handler(Looper.getMainLooper())

        fun scheduleJob(context: Context) {
            val componentName = ComponentName(context, NotificationService::class.java)
            val jobInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
                JobInfo.Builder(JOB_ID, componentName)
                    .setMinimumLatency(1)
                    .setOverrideDeadline(3000)
                    .build()
            } else {
                JobInfo.Builder(JOB_ID, componentName)
                    .setPeriodic(24 * 60 * 60 * 1000)
                    .build()
            }

            val jobScheduler =
                context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            jobScheduler.schedule(jobInfo)
        }
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        val hourOfDay = 12
        val minute = 0
        setNotificationAlarm(applicationContext, hourOfDay, minute)

        return false
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return false
    }

    private fun setNotificationAlarm(context: Context, hourOfDay: Int, minute: Int) {
        val currentCalendar = Calendar.getInstance()

        if (currentCalendar.get(Calendar.HOUR_OF_DAY) == hourOfDay &&
            currentCalendar.get(Calendar.MINUTE) == minute &&
            currentCalendar.get(Calendar.SECOND) == 0
        ) {
            sendNotification(context, hourOfDay, minute)
        } else {
            handler.postDelayed({
                setNotificationAlarm(context, hourOfDay, minute)
            }, CHECK_INTERVAL.toLong())
        }
    }

    @SuppressLint("ScheduleExactAlarm")
    private fun sendNotification(context: Context, hourOfDay: Int, minute: Int) {
        val notificationTime = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hourOfDay)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
        }
        val alarmIntent = Intent(context, NotificationReceiver::class.java).apply {
            action = "MY_NOTIFICATION_ACTION"
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            Log.e("ololo", "Уведомление в обработке")
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP, notificationTime.timeInMillis, pendingIntent
            )
        } else {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, notificationTime.timeInMillis, pendingIntent)
            Log.e("ololo", "Уведомление в обработке")
        }
    }
}