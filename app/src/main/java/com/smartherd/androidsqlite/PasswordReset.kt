package com.smartherd.androidsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_password_reset.*

class PasswordReset : AppCompatActivity() {

    private var firebase:FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_reset)

        firebase = FirebaseAuth.getInstance()

        btn_reset.setOnClickListener {

            ResetPassword()
        }
    }
    private fun ResetPassword(){

        var email = user_email.text.toString()

        if (TextUtils.isEmpty(email)){

            Toast.makeText(applicationContext, "Please enter ur email", Toast.LENGTH_SHORT).show()
        }else{

            firebase?.sendPasswordResetEmail(email)?.addOnCompleteListener(object : OnCompleteListener<Void>{
                override fun onComplete(task: Task<Void>) {

                    if (task.isSuccessful){

                        Toast.makeText(applicationContext, "Successfully sended the link to ur email", Toast.LENGTH_SHORT).show()
                    }else{

                        Toast.makeText(applicationContext, "ERROR", Toast.LENGTH_SHORT).show()
                    }
                }


            })
        }
    }
}
