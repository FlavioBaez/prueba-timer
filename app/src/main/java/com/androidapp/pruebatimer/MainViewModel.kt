package com.androidapp.pruebatimer

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidapp.pruebatimer.utils.toMinutesTimeString
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _restTime = MutableLiveData<String>()
    val restTime: LiveData<String>
        get() = _restTime

    private val _isTimerCountingDown = MutableLiveData<Boolean>()
    val isTimerCountingDown: LiveData<Boolean>
        get() = _isTimerCountingDown

    private val _onFinish = MutableLiveData<String>()
    val onFinish: LiveData<String>
        get() = _onFinish

    fun startCountDown(minutes: String, seconds: String) {
        _isTimerCountingDown.postValue(true)
        startCountDown(getMilliseconds(minutes, seconds))
    }


    private fun startCountDown(millis: Long) {
        viewModelScope.launch {
            object : CountDownTimer(millis, 1000) {
                override fun onTick(p0: Long) {
                    _restTime.postValue(p0.toMinutesTimeString())
                }

                override fun onFinish() {
                    _isTimerCountingDown.postValue(false)
                    _onFinish.postValue("Cuenta regresiva terminada")
                }

            }.start()
        }
    }

    private fun getMilliseconds(minutes: String, seconds: String): Long {
        var millis = 0L
        millis += if (minutes.isNotEmpty()) minutes.toLong() * 60000 else 0L
        millis += if (seconds.isNotEmpty()) seconds.toLong() * 1000 else 0L
        return millis
    }
}