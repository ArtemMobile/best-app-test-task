package com.example.bestapptesttask.data.network.interceptor

import android.util.Log
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject

class LoggingInterceptor @Inject constructor() : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        Log.i("HttpResponse", "log: $message")
    }
}