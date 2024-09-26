package com.example.eventapp773

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.eventapp773.databinding.ActivityMainBinding
import com.example.eventapp773.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEventCreate.setOnClickListener{
            val intent = Intent(this@MainActivity, CreateEventActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnEventRead.setOnClickListener{
            val intent = Intent(this@MainActivity, ReadEventActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}