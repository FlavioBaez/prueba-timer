package com.androidapp.pruebatimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.androidapp.pruebatimer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            viewModel = mainViewModel
            lifecycleOwner = this@MainActivity
        }
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            mainViewModel.startCountDown(binding.etMinutes.text.toString(),binding.etSeconds.text.toString())
        }

        observe()
    }

    private fun observe(){
        mainViewModel.onFinish.observe(this,{
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
        })
    }
}