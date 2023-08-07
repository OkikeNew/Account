package com.tolgayeni.accountdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tolgayeni.accountdemo.databinding.ActivityMainBinding

private lateinit var auth:FirebaseAuth
private var binding:ActivityMainBinding?=null
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        auth= Firebase.auth

        binding?.btnSignOut?.setOnClickListener{
            if (auth.currentUser!=null){
                auth.signOut()
                startActivity(Intent(this,GetStartedActivity::class.java))
                finish()
            }
        }
    }
}