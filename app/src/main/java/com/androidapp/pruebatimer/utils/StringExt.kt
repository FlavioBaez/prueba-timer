package com.androidapp.pruebatimer.utils

import java.util.concurrent.TimeUnit

fun Long.toMinutesTimeString() =
    String.format("%02d:%02d",
        TimeUnit.MILLISECONDS.toMinutes(this) -
                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(this)),
        TimeUnit.MILLISECONDS.toSeconds(this) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(this)))