package com.example.matala2


import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class EditStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        // Set up the toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Edit Student"
            setDisplayHomeAsUpEnabled(true) // Show back button
        }

        // Handle back button click
        toolbar.setNavigationOnClickListener {
            finish() // Go back to the previous page
        }

        val studentIndex = intent.getIntExtra("studentIndex", -1)
        val student = StudentRepository.students[studentIndex]

        // Display the static student image
        findViewById<ImageView>(R.id.studentImage).setImageResource(R.drawable.ic_student)

        val nameInput = findViewById<EditText>(R.id.nameInput)
        val idInput = findViewById<EditText>(R.id.idInput)
        val phoneInput = findViewById<EditText>(R.id.phoneInput)
        val addressInput = findViewById<EditText>(R.id.addressInput)
        val isCheckedInput = findViewById<CheckBox>(R.id.isCheckedInput)

        // Pre-fill fields with existing data
        nameInput.setText(student.name)
        idInput.setText(student.id)
        phoneInput.setText(student.phone)
        addressInput.setText(student.address)
        isCheckedInput.isChecked = student.isChecked

        val saveButton = findViewById<Button>(R.id.saveButton)
        val deleteButton = findViewById<Button>(R.id.deleteButton)
        val cancelButton = findViewById<Button>(R.id.cancelButton)

        saveButton.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val id = idInput.text.toString().trim()

            if (name.isEmpty()) {
                nameInput.error = "Name is required"
                return@setOnClickListener
            }

            if (id.isEmpty()) {
                idInput.error = "ID is required"
                return@setOnClickListener
            }

            student.name = name
            student.id = id
            student.phone = phoneInput.text.toString().trim()
            student.address = addressInput.text.toString().trim()
            student.isChecked = isCheckedInput.isChecked

            val intent = Intent(this, StudentsListActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)

            Toast.makeText(this, "Student updated successfully", Toast.LENGTH_SHORT).show()

            finish()
        }

        deleteButton.setOnClickListener {
            StudentRepository.students.removeAt(studentIndex)

            val intent = Intent(this, StudentsListActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)

            Toast.makeText(this, "Student deleted", Toast.LENGTH_SHORT).show()

            finish()
        }

        cancelButton.setOnClickListener {
            finish()
        }
    }
}

