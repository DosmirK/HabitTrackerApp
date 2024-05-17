package com.example.habittrackerapp.domain.utils.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null) {
            val notificationHelper = NotificationHelper(context)
            Log.e("ololo", "Уведомление создаётся!")
            notificationHelper.createNotification("Напоминание", "Не забудь про ежедневные привычки!")
        }
    }
}