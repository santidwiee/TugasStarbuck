package com.example.tugasstarbuck

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.*
import com.example.tugasstarbuck.identity.LoginActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    /**
     * by : Santi Dwi Agustin
     * date : 0ct 21th, 2019
     */
    /**
     * untuk membuat spalshscreen perlu ikuti langkah berikut :
     * step 1 : siapkan @drawable gradiant 1-3 dan gradient_list serta logo starbuck
     * step 2 : buat tampilan layout terlebih dulu
     * step 3 : gunakan NoAppTheme di manifest-nya untuk activtiy Splashscreen
     * step 4 : atur duration splash selama postDelayed berlangsung sebanyak 4 detik
     * step 5 : jangan lupa untuk membuat animasi pada logonya
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //membuat gambar logo fadein
        imgLogo.visibility = View.VISIBLE
        val anim3 = AnimationUtils.loadAnimation(this, R.anim.fadein)
        imgLogo.startAnimation(anim3)
        slogan.startAnimation(anim3)

        //membuat splash selama 4 detik
        Handler().postDelayed({
            //ini untuk start activity-nya
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            //untuk mengakhiri activity ini
            finish()
        }, 4000)
    }
}
