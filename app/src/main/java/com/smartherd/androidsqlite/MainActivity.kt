package com.smartherd.androidsqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private var firebaseAuth:FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseAuth = FirebaseAuth.getInstance()

        login_btn.setOnClickListener {
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        }

        signup_btn?.setOnClickListener {
            RegisterNewUser()
        }
    }
    private fun RegisterNewUser(){
        val email_text = user_email?.text.toString()
        var password = user_password?.text.toString()

        if (TextUtils.isEmpty(email_text) && TextUtils.isEmpty(password)){

            Toast.makeText(applicationContext, "this field connot be empty", Toast.LENGTH_SHORT).show()
        }
        else{

            firebaseAuth?.createUserWithEmailAndPassword(email_text,password)?.addOnCompleteListener(object : OnCompleteListener<AuthResult>{
                override fun onComplete(task: Task<AuthResult>) {

                    if (task.isSuccessful){
                        Toast.makeText(applicationContext, "Account Created", Toast.LENGTH_SHORT).show()
                    }else{

                        Toast.makeText(applicationContext, "Accounted can't be Create", Toast.LENGTH_SHORT).show()
                    }

                }


            })
        }
    }
}


