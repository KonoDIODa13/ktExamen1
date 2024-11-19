package com.example.examenjaimegonzalez.Model

import android.os.CountDownTimer

class Timer(millisInFuture: Long, countDownInterval: Long) :
    CountDownTimer(millisInFuture, countDownInterval) {
    override fun onTick(millisUntilFinished: Long) {
        TODO("Not yet implemented")
    }

    override fun onFinish() {
        TODO("Not yet implemented")
    }
}