package com.example.matala2

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class StudentDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        // Set up the toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Student Details"
            setDisplayHomeAsUpEnabled(true) // Show back button
        }

        // Handle back button click
        toolbar.setNavigationOnClickListener {
            finish() // Go back to the previous page
        }

        val studentIndex = intent.getIntExtra("studentIndex", -1)
        val student = StudentRepository.students[studentIndex]

        findViewById<ImageView>(R.id.studentImage).setImageResource(R.drawable.ic_student)
        findViewById<TextView>(R.id.nameText).text = student.name
        findViewById<TextView>(R.id.idText).text = student.id
        findViewById<TextView>(R.id.phoneText).text = student.phone
        findViewById<TextView>(R.id.addressText).text = student.address
        findViewById<TextView>(R.id.isCheckedText).text = if (student.isChecked) "Yes" else "No"

        findViewById<Button>(R.id.editButton).setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("studentIndex", studentIndex)
            startActivity(intent)
        }
    }
}
