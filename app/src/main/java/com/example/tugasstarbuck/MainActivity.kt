package com.example.tugasstarbuck

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.animation.*
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ViewFlipper
import com.example.tugasstarbuck.map.MapsActivity
import com.example.tugasstarbuck.menu.MenuListActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var promo = intArrayOf(R.drawable.promo_1, R.drawable.promo_2, R.drawable.promo_3, R.drawable.promo_4, R.drawable.promo_5)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //membuat gambar logo rotate
        imgMap.visibility = View.VISIBLE
        imgMenu.visibility = View.VISIBLE
        val anim3 = AnimationUtils.loadAnimation(this, R.anim.fadein)
        imgMap.startAnimation(anim3)
        imgMenu.startAnimation(anim3)

        /**
         * untuk animation rotate ada dua opsional bisa pakai diatas atau dibawah ini :
         *
         * val rotateMap = ObjectAnimator.ofFloat(imgMap, View.ROTATION, -360f, 0f)
        rotateMap.setDuration(5000)
        rotateMap.interpolator = AccelerateInterpolator()
        rotateMap.start()
         */

        //perintah untuk button close
        close.setOnClickListener {
            finish()
        }

        //flipper
        val viewFlipper = findViewById<ViewFlipper>(R.id.Flipper)
        if (viewFlipper != null) {
            viewFlipper.setInAnimation(applicationContext, android.R.anim.slide_in_left)
            viewFlipper.setOutAnimation(applicationContext, android.R.anim.slide_out_right)
        }

        if (viewFlipper != null) {
            for (image in promo) {
                val imageView = ImageView(this)
                val layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                layoutParams.gravity = Gravity.CENTER
                imageView.layoutParams = layoutParams
                imageView.setImageResource(image)
                viewFlipper.addView(imageView)
            }
        }


            //perintah button map
            map.setOnClickListener {
                startActivity(Intent(this@MainActivity, MapsActivity::class.java))
            }

            //perintah button menu
            menu.setOnClickListener {
                startActivity(Intent(this@MainActivity, MenuListActivity::class.java))
            }
    }
}
