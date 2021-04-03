package com.android.setupschool.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.android.setupschool.R
import com.android.setupschool.utils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        val navController = findNavController(R.id.container_host)

        navController.addOnDestinationChangedListener { _, _, _ ->
            hideKeyboard()
        }
    }
}