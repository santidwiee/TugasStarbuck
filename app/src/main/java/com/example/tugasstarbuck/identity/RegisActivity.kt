package com.example.tugasstarbuck.identity

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import androidx.room.Room
import com.example.tugasstarbuck.R
import com.example.tugasstarbuck.identity.Room.AppDatabase
import com.example.tugasstarbuck.identity.Room.userDAO
import com.example.tugasstarbuck.identity.Room.usermodel
import kotlinx.android.synthetic.main.activity_regis.*

class RegisActivity : AppCompatActivity() {

    private var edtName : EditText? = null
    private var edtEmail: EditText? = null
    private var edtPassword: EditText? = null
    private var progressDialog: ProgressDialog? = null

    private var userDao: userDAO? = null

    private val isEmpty: Boolean
        get() = if (TextUtils.isEmpty(edtEmail!!.text.toString()) ||
            TextUtils.isEmpty(edtPassword!!.text.toString()) ||
            TextUtils.isEmpty(edtName!!.text.toString())
        ) {
            true
        } else {
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regis)

        progressDialog = ProgressDialog(this)
        progressDialog!!.setCancelable(false)
        progressDialog!!.setMessage("Registering...")
        progressDialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog!!.progress = 0

        //identifikasi atribut editext
        edtName = findViewById(R.id.Regisusername)
        edtEmail = findViewById(R.id.Regisemail)
        edtPassword = findViewById(R.id.Regispassword)

        //mengaktifkan gradient_list
        val animationDrawable2 = regis.getBackground() as AnimationDrawable
        animationDrawable2.setEnterFadeDuration(3000)
        animationDrawable2.setExitFadeDuration(3000)
        animationDrawable2.start()

        userDao = Room.databaseBuilder(this, AppDatabase::class.java!!, "mi-database.db")
            .allowMainThreadQueries()
            .build()
            .userDao


        signUpRegis.setOnClickListener {
            if (!isEmpty) {
                progressDialog!!.show()
                Handler().postDelayed({
                    val user = usermodel(
                        edtName!!.text.toString(),
                        edtEmail!!.text.toString(),
                        edtPassword!!.text.toString()
                    )
                    userDao!!.insert(user)
                    progressDialog!!.dismiss()
                    startActivity(Intent(this@RegisActivity, LoginActivity::class.java))
                }, 1000)

            } else {
                Toast.makeText(this@RegisActivity, "Empty Fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
