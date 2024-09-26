package com.example.eventapp773

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.eventapp773.databinding.ActivityCreateEventBinding
import com.example.eventapp773.databinding.ActivityReadEventBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Locale

class CreateEventActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateEventBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHomePage.setOnClickListener{
            val intent = Intent(this@CreateEventActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnCreate.setOnClickListener{

            val db = Firebase.firestore

            val name = binding.eventCreateName.text.toString()
            val description = binding.eventCreateDescription.text.toString()
            val localization = binding.eventCreateLocalization.text.toString()
            val price = binding.eventCreatePrice.text.toString()

            val event = hashMapOf(
                "name" to name,
                "description" to description,
                "localization" to localization,
                "price" to price,
                "subs" to ""
            )

// Add a new document with a generated ID
            db.collection("events")
                .add(event)
                .addOnSuccessListener { documentReference ->
                    Log.d("F-STORE: ", "DocumentSnapshot added with ID: ${documentReference.id}")
                    Toast.makeText(this, "Event Created", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@CreateEventActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .addOnFailureListener { e ->
                    Log.w("F-STORE: ", "Error adding document", e)
                    Toast.makeText(this, "Event Create Failed", Toast.LENGTH_SHORT).show()
                    println("404 EVENT")
                }
        }

    }
}