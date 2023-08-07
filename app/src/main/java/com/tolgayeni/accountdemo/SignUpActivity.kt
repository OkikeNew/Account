package com.tolgayeni.accountdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import com.tolgayeni.accountdemo.databinding.ActivitySignUpBinding

private var binding:ActivitySignUpBinding?=null
private lateinit var auth:FirebaseAuth

class SignUpActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        auth=Firebase.auth

        binding?.tvLoginPage?.setOnClickListener{

            startActivity(Intent(this,SignInActivity::class.java))
            finish()

        }

        binding?.btnSignUp?.setOnClickListener{
            registerUser()
        }
    }
    private fun registerUser(){
        val name= binding?.etSinUpName?.text.toString()
        val email= binding?.etSinUpEmail?.text.toString()
        val password=binding?.etSinUpPassword?.text.toString()

        if(validateForm(name,email,password)){

            showProgressBar()
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
                task->
                if(task.isSuccessful){
                    showToast(this,"Başarıyla kayıt yapıldı")
                    hideProgressBar()
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()
                }else{
                showToast(this,"Kayıt yapılamadı. Bir daha deneyiniz")
                hideProgressBar()
                }
            }

        }
    }
    private fun validateForm(name:String,email:String,password:String):Boolean{
        return when {
            TextUtils.isEmpty(name)->{
                binding?.tilName?.error = "Enter name"
                false
            }
            TextUtils.isEmpty(email) && !Patterns.EMAIL_ADDRESS.matcher(email).matches()->{
                binding?.tilEmail?.error = "Enter valid email address"
                false
            }
            TextUtils.isEmpty(password)->{
                binding?.tilPassword?.error = "Enter password"
                false
            }
            else -> { true }
        }
    }
}