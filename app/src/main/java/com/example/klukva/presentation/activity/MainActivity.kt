package com.example.klukva.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.klukva.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Launcher)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}