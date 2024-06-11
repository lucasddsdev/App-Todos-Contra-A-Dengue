package com.example.todoscontraadengue

import EmailViewModel
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var emailViewModel: EmailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val subjectEditText: EditText = findViewById(R.id.subjectEditText)
        val nameEditText: EditText = findViewById(R.id.nameEditText)
        val descriptionEditText: EditText = findViewById(R.id.descriptionEditText)
        val sendButton: Button = findViewById(R.id.sendButton)
        val responseTextView: TextView = findViewById(R.id.responseTextView)

        emailViewModel = ViewModelProvider(this).get(EmailViewModel::class.java)

        emailViewModel.response.observe(this, Observer { response ->
            responseTextView.text = response
            if (response == "Email enviado com sucesso!") {
                // Reseta os campos após o envio bem-sucedido
                subjectEditText.text.clear()
                nameEditText.text.clear()
                descriptionEditText.text.clear()
            }
        })

        sendButton.setOnClickListener {
            val subject = subjectEditText.text.toString()
            val name = nameEditText.text.toString()
            val description = descriptionEditText.text.toString()

            emailViewModel.sendEmail(subject, name, description)
        }
    }
}
