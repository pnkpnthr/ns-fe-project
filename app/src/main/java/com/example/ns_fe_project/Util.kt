package com.example.ns_fe_project

import android.widget.ImageView
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int) {
    this.supportFragmentManager.beginTransaction().replace(frameId, fragment).commit()
}
fun ImageView.loadImage(url: String?) {
    Glide.with(this.context).load(url).into(this)
}

fun Fragment.onBackPressDispatcher(callback: () -> Unit) {
    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, true) {
        callback()
    }
}