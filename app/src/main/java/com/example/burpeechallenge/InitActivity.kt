package com.example.burpeechallenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_init.*


class InitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_init)

        nameEnterButton.setOnClickListener {
            val name = editName.text.toString()
            d("jomic", "pressed by $name!")

            startActivity(Intent(this, HomeActivity::class.java))
        }

    }

}
