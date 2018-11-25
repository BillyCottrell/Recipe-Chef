package com.codexive.recipechef.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.codexive.recipechef.R


class MainActivity : AppCompatActivity() {

    private lateinit var mFirebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }
}
