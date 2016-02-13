package team.hackm.android.qolk.util

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

fun AppCompatActivity.setContentFragment(@IdRes container: Int, fragment: Fragment) {
    this.supportFragmentManager.beginTransaction().replace(container, fragment).commit()
}