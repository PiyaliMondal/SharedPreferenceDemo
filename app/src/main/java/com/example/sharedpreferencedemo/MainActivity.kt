package com.example.sharedpreferencedemo

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var nameText:EditText // nameText is an object of type EditText
    private lateinit var ageText:EditText // ageText is an object of type EditText
    private lateinit var sf:SharedPreferences
    private lateinit var editor:SharedPreferences.Editor // Used to edit or add values for shared preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameText = findViewById(R.id.etName)
        ageText = findViewById(R.id.etAge)

        // Create two reference variables for shared preferences

        sf = getSharedPreferences("my_sf", MODE_PRIVATE)
        editor = sf.edit()


    }

    override fun onPause() {
        super.onPause()

        val name = nameText.text.toString()
        val age = ageText.text.toString().toInt()
        editor.apply {
            putString("sf_name",name)
            putInt("sf_age",age)
            commit() // To save the values
        }

    }

    override fun onResume() {
        super.onResume()

        val name = sf.getString("sf_name",null)
        val age = sf.getInt("sf_age",0)
        nameText.setText(name)
        if (age != 0) {
            ageText.setText(age.toString())
        }

    }
}