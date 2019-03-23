package test.org.kastatest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import test.org.kastatest.ui.campaigns.CampaignsFragment
import test.org.kastatest.ui.pushView.PushFragment
import test.org.kastatest.utils.ActivityUtils
import test.org.kastatest.utils.Constants


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ActivityUtils.addFragmentToActivity(this, CampaignsFragment(), true)
        checkPlayServices()
        settingNotificationChanel()
        checkIntent()
        performWorkWithFirebase()

    }

    override fun onResume() {
        super.onResume()
        checkPlayServices()
    }

    private fun checkIntent() {
        intent?.extras?.let {
            if (intent!!.extras!!.getString(Constants.imgTrigger) != null) {
                ActivityUtils.addFragmentToActivity(this, PushFragment.newInstance(intent!!.extras!!.getString(Constants.imgTrigger)), true)
            } else if (intent!!.extras!!.getString(Constants.activityParam) != null) {
                ActivityUtils.addFragmentToActivity(this, PushFragment.newInstance(intent!!.extras!!.getString(Constants.activityParam)), true)
            }
        }
    }

    private fun settingNotificationChanel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            val channelId = getString(R.string.default_notification_channel_id)
            val channelName = getString(R.string.default_notification_channel_name)
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_LOW))
        }

    }

    private fun performWorkWithFirebase() {
        FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        return@OnCompleteListener
                    }
                    // Get new Instance ID token
                    val token = task.result?.token
                    Log.d("myLog", "token is $token")
                })
    }

    private fun checkPlayServices() {
        val googleAPI = GoogleApiAvailability.getInstance()
        val result = googleAPI.isGooglePlayServicesAvailable(this)
        if (result != ConnectionResult.SUCCESS) {
            if (googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(this, result, playServicesResolutionRequest).show()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val fragmentManager = this.supportFragmentManager
        if (fragmentManager.backStackEntryCount == 0) {
            finish()
        }
    }

    companion object {
        const val playServicesResolutionRequest = 9000
    }
}
