package test.org.kastatest.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.util.Log
import test.org.kastatest.R

object ActivityUtils {

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