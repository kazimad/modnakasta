package test.org.kastatest.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import test.org.kastatest.R

object ActivityUtils {

    private val TAG = ActivityUtils::class.java.simpleName

    fun addFragmentToActivity(activity: FragmentActivity, fragment: Fragment) {
        addFragmentToActivity(activity, fragment, false)
    }

    fun addFragmentToActivity(activity: FragmentActivity, fragment: Fragment, addToBackStack: Boolean) {
        addFragmentToActivity(activity, fragment, addToBackStack, R.id.fragmentContainer)
    }

    private fun addFragmentToActivity(activity: FragmentActivity, fragment: Fragment, addToBackStack: Boolean, containerId: Int) {
        val fragmentManager = activity.supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(containerId, fragment)
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.commitAllowingStateLoss()
    }
}