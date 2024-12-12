package com.example.matala2


import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class NewStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_student)

        // Set up the toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Add Student"
            setDisplayHomeAsUpEnabled(true) // Show back button
        }

        // Handle back button click
        toolbar.setNavigationOnClickListener {
            finish() // Go back to the previous page
        }

        // Display the static student image
        findViewById<ImageView>(R.id.studentImage).setImageResource(R.drawable.ic_student)

        // References to input fields
        val nameInput = findViewById<EditText>(R.id.nameInput)
        val idInput = findViewById<EditText>(R.id.idInput)
        val phoneInput = findViewById<EditText>(R.id.phoneInput)
        val addressInput = findViewById<EditText>(R.id.addressInput)
        val isCheckedInput = findViewById<CheckBox>(R.id.isCheckedInput)

        // Buttons
        val saveButton = findViewById<Button>(R.id.saveButton)
        val cancelButton = findViewById<Button>(R.id.cancelButton)

        // Save button logic
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

            val student = Student(
                name = name,
                id = id,
                phone = phoneInput.text.toString().trim(),
                address = addressInput.text.toString().trim(),
                isChecked = isCheckedInput.isChecked
            )

            // Add the new student to the repository
            StudentRepository.students.add(student)
            Toast.makeText(this, "Student added successfully", Toast.LENGTH_SHORT).show()
            finish() // Close this activity and return to the previous screen
        }

        // Cancel button logic
        cancelButton.setOnClickListener {
            finish() // Close this activity without saving
        }
    }
}
