package com.net.machinetest.ui.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.machinetest1.R
import dagger.hilt.android.AndroidEntryPoint

class NewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
    }
}