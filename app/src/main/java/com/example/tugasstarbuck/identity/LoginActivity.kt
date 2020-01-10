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
import com.example.tugasstarbuck.MainActivity
import com.example.tugasstarbuck.R
import com.example.tugasstarbuck.identity.Room.*
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    /**
     * membuat login :
     * step 1 : membuat shape untuk editText dan Button di drawable
     * step 2 : membuat activtiy layout login dan registrasi terlebih dulu
     * step 3 : tambahkan use permisson internet pada manifest
     * step 4 : karena database yang digunakan adalah room, maka perlu adanya dao, entity dan appdatabase
     *          - pertama buat modelnya atau entitynya terlebih dulu dengan nama usermodel
     *          - kedua dengan membuat database access object (DAO) dengan nama userDAO
     *          - ketiga membuat room database dengan nama AppDatabase
     * step 5 : lalu
     */


    private var edtEmail: EditText? = null
    private var edtPassword: EditText? = null
    private var progressDialog: ProgressDialog? = null

    private var db: AppDatabase? = null
    private var userDao: userDAO? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //identifikasi atribut edittext
        edtEmail = findViewById(R.id.Loginemail)
        edtPassword = findViewById(R.id.Loginpassword)

        //memunculkan progreesdialog saat value dari entity diproses
        progressDialog = ProgressDialog(this)
        progressDialog!!.setCancelable(false)
        progressDialog!!.setMessage("Check User...")
        progressDialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog!!.progress = 0


        //mengaktifkan gradient_list
        val animationDrawable2 = login.getBackground() as AnimationDrawable
        animationDrawable2.setEnterFadeDuration(3000)
        animationDrawable2.setExitFadeDuration(3000)
        animationDrawable2.start()

        //untuk mengaktifkan room database yang sudah dibuat dan diimpelementasikan ke dalam datatbase bernama
        // "user.db"
        db = Room.databaseBuilder(this, AppDatabase::class.java!!, "mi-database.db")
            .allowMainThreadQueries()
            .build()


        userDao = db!!.userDao

        //mengaktifkan perintah pada button signup
        signUpLogin.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisActivity::class.java))
        }

        //mengaktifkan perintah pada button signIn
        signInLogin.setOnClickListener {
            if (!emptyValidation()) {
                progressDialog!!.show()
                Handler().postDelayed({
                    //mengambil value dari entity userDao
                    val user = userDao!!.getUser(edtEmail!!.text.toString(), edtPassword!!.text.toString())

                    //jika atribut edtEmail dan edtPassword ada isinya, maka akan di passsing dengan putExtra sesuai
                    //field dari model "usermodel"
                    if (user != null) {
                        val i = Intent(this@LoginActivity, MainActivity::class.java)
                        i.putExtra("usermodel", user)
                        startActivity(i)
                        finish()
                    }
                    //kalo tidak, maka akan memunculkan message
                    else {
                        Toast.makeText(this@LoginActivity, "Unregistered user, or incorrect", Toast.LENGTH_SHORT).show()
                    }
                    //progressdialog akan berhenti setelah 1 detik
                    progressDialog!!.dismiss()
                }, 1000)

            }
            //kalo tidak valid maka akan memunculkan message dibawah
            else {
                Toast.makeText(this@LoginActivity, "Empty Fields", Toast.LENGTH_SHORT).show()
            }
        }


    }

    //perintah untuk mecocokkan isi dari atribut editEmail dan edtPassword
    private fun emptyValidation(): Boolean {
        return if (TextUtils.isEmpty(edtEmail!!.text.toString()) && TextUtils.isEmpty(edtPassword!!.text.toString())) {
            true
        } else {
            false
        }
    }
}

