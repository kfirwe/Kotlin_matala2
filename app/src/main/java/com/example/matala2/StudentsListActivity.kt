package com.example.matala2

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StudentsListActivity : AppCompatActivity() {

    private var backPressedTime: Long = 0 // To track back button press time

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students_list)

        // Set up the toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Student List"
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val addStudentButton = findViewById<FloatingActionButton>(R.id.addStudentButton)

        recyclerView.layoutManager = LinearLayoutManager(this)

        // Add a divider between items
        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)

        recyclerView.adapter = StudentAdapter(StudentRepository.students) { student ->
            openStudentDetails(student)
        }

        addStudentButton.setOnClickListener {
            val intent = Intent(this, NewStudentActivity::class.java)
            startActivity(intent)
        }
    }

    private fun openStudentDetails(student: Student) {
        val intent = Intent(this, StudentDetailsActivity::class.java)
        intent.putExtra("studentIndex", StudentRepository.students.indexOf(student))
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        findViewById<RecyclerView>(R.id.recyclerView).adapter?.notifyDataSetChanged()
    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() - backPressedTime < 2000) {
            super.onBackPressed() // Exit the app if back is pressed twice quickly
        } else {
            backPressedTime = System.currentTimeMillis()
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show()
        }
    }
}
