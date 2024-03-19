package com.example.mvx.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.lifecycle.ViewModelProvider
import com.example.mvx.R
import com.example.mvx.viewmodel.MyViewModel

class MyActivity : AppCompatActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelV2 = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MyViewModel::class.java)
        setContent {
            MyMainView(viewModelV2)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_jingziqi, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_reset -> {
                val viewModel: MyViewModel? by viewModels()
                viewModel!!.onResetSelected()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}