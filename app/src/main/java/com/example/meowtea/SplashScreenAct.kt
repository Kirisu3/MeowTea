package com.example.meowtea

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashScreenAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        iv_note.alpha = 0f
        iv_note.animate().setDuration(1500).alpha(1f).withEndAction }
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
        overidePendingTransition(andriod.R.anim.fade_in,andriod.R.anim.fade_out)
        finish()
       }
    }
}