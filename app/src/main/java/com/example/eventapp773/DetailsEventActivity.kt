package com.example.eventapp773

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.eventapp773.databinding.ActivityDetailsEventBinding
import com.example.eventapp773.databinding.ActivityReadEventBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Objects

class DetailsEventActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var dataList: ArrayList<DataClass>
    private lateinit var binding: ActivityDetailsEventBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_event)

        binding = ActivityDetailsEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHomePage.setOnClickListener{
            val intent = Intent(this@DetailsEventActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        dataList = arrayListOf()
        val receivedInt = intent.getIntExtra("key_position", 0)

        getEvent(receivedInt)
    }

    private fun getEvent(keyPosition: Int){
        db = FirebaseFirestore.getInstance()
        db.collection("events")
            .get()
            .addOnSuccessListener { result ->
                dataList.clear()
                for (document in result) {
                    val documentId = document.id
                    val event = document.toObject(DataClass::class.java)
                    event.id = documentId
                    dataList.add(event)
                }

                val currentEvent = dataList[keyPosition]
                Log.d("DETAILS_EVENT_ACTIVITY 1: ", currentEvent.toString())

                val dName = findViewById<TextView>(R.id.d_event_name)
                val dLocalization = findViewById<TextView>(R.id.d_event_localization)
                val dDescription = findViewById<TextView>(R.id.d_event_description)
                val dPrice = findViewById<TextView>(R.id.d_event_price)

                dName.text = currentEvent.name
                dLocalization.text = currentEvent.localization
                dDescription.text = currentEvent.description
                dPrice.text = currentEvent.price

            }
            .addOnFailureListener { exception ->
                Log.w("DETAILS_EVENT_ACTIVITY: ", "Error getting documents.", exception)
            }

    }
}