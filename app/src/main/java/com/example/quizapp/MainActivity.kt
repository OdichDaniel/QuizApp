package com.example.quizapp

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.contentLayout.submitButton.setOnClickListener {
            val answerOneId = binding.contentLayout.radioGroup.checkedRadioButtonId
            val isAnswerTwoCorrect = binding.contentLayout.qnTwoSecondChoice.isChecked
            var marks = 0
            if(answerOneId == binding.contentLayout.qnOneSecondChoice.id){
                marks+=50
            }
            if(isAnswerTwoCorrect){
                marks+=50
            }
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
            val date = dateFormat.format(Date())

            val dialog = AlertDialog.Builder(this)
            dialog.setMessage("Congratulations! You submitted on $date, Your achieved $marks%")
            dialog.setCancelable(false)
            dialog.setPositiveButton("Ok") { _, _ ->
                marks = 0
            }
            dialog.show()
        }
        binding.contentLayout.resetButton.setOnClickListener {
            binding.contentLayout.radioGroup.clearCheck()
            binding.contentLayout.qnTwoFirstChoice.isChecked = false
            binding.contentLayout.qnTwoSecondChoice.isChecked = false
            binding.contentLayout.qnTwoThirdChoice.isChecked = false;
        }
        binding.contentLayout.qnTwoFirstChoice.setOnClickListener {
            if(binding.contentLayout.qnTwoSecondChoice.isChecked || binding.contentLayout.qnTwoThirdChoice.isChecked){
                binding.contentLayout.qnTwoSecondChoice.isChecked = false
                binding.contentLayout.qnTwoThirdChoice.isChecked = false
            }
        }
        binding.contentLayout.qnTwoSecondChoice.setOnClickListener {
            if(binding.contentLayout.qnTwoFirstChoice.isChecked || binding.contentLayout.qnTwoThirdChoice.isChecked){
                binding.contentLayout.qnTwoFirstChoice.isChecked = false
                binding.contentLayout.qnTwoThirdChoice.isChecked = false
            }
        }
        binding.contentLayout.qnTwoThirdChoice.setOnClickListener {
            if(binding.contentLayout.qnTwoFirstChoice.isChecked || binding.contentLayout.qnTwoThirdChoice.isChecked){
                binding.contentLayout.qnTwoFirstChoice.isChecked = false
                binding.contentLayout.qnTwoSecondChoice.isChecked = false
            }
        }
    }
}