package test.org.kastatest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId

import test.org.kastatest.ui.campaigns.CampaignsFragment
import test.org.kastatest.utils.ActivityUtils

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ActivityUtils.addFragmentToActivity(this, CampaignsFragment(), true)
        performWorkWithFirebase()
    }

    private fun performWorkWithFirebase() {
        FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        return@OnCompleteListener
                    }
                    // Get new Instance ID token
                    val token = task.result?.token
                    // Log and toast
                    val msg = getString(R.string.msg_token_fmt, token)
                    Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                })
    }

}
