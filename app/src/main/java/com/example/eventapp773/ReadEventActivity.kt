package com.example.eventapp773

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eventapp773.databinding.ActivityMainBinding
import com.example.eventapp773.databinding.ActivityReadEventBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class ReadEventActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<DataClass>
    private lateinit var adapterClass: AdapterClass
    private lateinit var db: FirebaseFirestore
    private lateinit var binding: ActivityReadEventBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_event)

        binding = ActivityReadEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHomePage.setOnClickListener{
            val intent = Intent(this@ReadEventActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        dataList = arrayListOf()
        adapterClass = AdapterClass(dataList)
        recyclerView.adapter = adapterClass

        EventChangeListener()

        adapterClass.setOnItemClickListener(object: AdapterClass.onItemClickListener{
            override fun onItemClick(position: Int) {
                Log.d("EVENT CLICK DETECTED: ", position.toString())

                val intent = Intent(this@ReadEventActivity, DetailsEventActivity::class.java)
                intent.putExtra("key_position", position)
                startActivity(intent)
                finish()
            }

        })
    }

    private fun EventChangeListener(){
        db = FirebaseFirestore.getInstance()
        db.collection("events")
//                    .orderBy("data", Query.Direction.ASCENDING)
                    .get()
                    .addOnSuccessListener { result ->
                        dataList.clear()
                        for (document in result) {
                            val documentId = document.id
                            val event = document.toObject(DataClass::class.java)
                            event.id = documentId
                            dataList.add(event)
                        }

                         adapterClass.notifyDataSetChanged()

                         Log.d("F-STORE: ", dataList[0].toString())
                    }
                    .addOnFailureListener { exception ->
                        Log.w("F-STORE: ", "Error getting documents.", exception)
                    }

    }
}
