package com.robpridham.chucknorrisapp.ui.activity

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.robpridham.chucknorrisapp.R

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
