package com.smartherd.androidsqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private var firebaseAuth:FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        firebaseAuth = FirebaseAuth.getInstance()

        reset_the_password.setOnClickListener {
            startActivity(Intent(this@LoginActivity, PasswordReset::class.java))
        }

        login_btn_activity.setOnClickListener {

            LoginUser()
        }
    }
    private fun LoginUser(){

        var email = user_email_login.text.toString()
        var password = user_password_login.text.toString()

        if (TextUtils.isEmpty(email)&& TextUtils.isEmpty(password)){

            Toast.makeText(applicationContext, "this field canoot be empty", Toast.LENGTH_SHORT).show()
        }else{

            firebaseAuth?.signInWithEmailAndPassword(email, password)?.addOnCompleteListener(object : OnCompleteListener<AuthResult>{
                override fun onComplete(task: Task<AuthResult>) {

                    if (task.isSuccessful){
                        Toast.makeText(applicationContext, "Login Successfully", Toast.LENGTH_SHORT).show()
                    }else{

                        Toast.makeText(applicationContext, "ERROR", Toast.LENGTH_SHORT).show()
                    }

                }


            })
        }
    }
}
