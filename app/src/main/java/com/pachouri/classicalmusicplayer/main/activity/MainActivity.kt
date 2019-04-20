package com.pachouri.classicalmusicplayer.main.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.pachouri.classicalmusicplayer.R
import com.pachouri.classicalmusicplayer.main.fragment.AlbumsListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        attachFragment(AlbumsListFragment())
    }

    fun attachFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val ft = fragmentManager.beginTransaction()
        ft.add(R.id.container, fragment)
        ft.commitAllowingStateLoss()
    }
}
