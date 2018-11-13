package com.robpridham.chucknorrisapp.ui.activity

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.robpridham.chucknorrisapp.R
import com.robpridham.chucknorrisapp.ui.fragment.MainFragment

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.content_fragment, MainFragment(), "main_fragment").commitNow()
    }
}
