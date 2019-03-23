package test.org.kastatest.fcm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.text.TextUtils
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import test.org.kastatest.MainActivity
import test.org.kastatest.R
import test.org.kastatest.utils.Constants

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        remoteMessage?.data?.isNotEmpty()?.let {
            sendNotification(remoteMessage.data!![Constants.imgTrigger])
        }


//        remoteMessage?.notification?.let { it ->
//            it.body?.let {
//                if (it.contains(Constants.imgTrigger)) {
//                    sendNotification(it)
//                }
//            }
//        }
    }

    private fun sendNotification(messageBody: String?) {
        if (!TextUtils.isEmpty(messageBody)) {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(Constants.activityParam, messageBody)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                    PendingIntent.FLAG_ONE_SHOT)

            val channelId = getString(R.string.default_notification_channel_id)
            val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val notificationBuilder = NotificationCompat.Builder(this, channelId)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle(getString(R.string.fcm_message))
                    .setContentText(messageBody)
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent)

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(channelId,
                        "Channel human readable title",
                        NotificationManager.IMPORTANCE_DEFAULT)
                notificationManager.createNotificationChannel(channel)
            }
            notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
        }
    }
}