package edu.noctrl.fall23_330.mulch_wegnero

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import edu.noctrl.fall23_330.mulch_wegnero.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        val nextButton: Button = findViewById(R.id.next_button)
        val premiumBark: Button = findViewById(R.id.premium_bark)
        val specialBlend: Button = findViewById(R.id.special)
        val tripleGround: Button = findViewById(R.id.triple_ground)
        val chocDyed: Button = findViewById(R.id.chocolate_dyed)
        val redDyed: Button = findViewById(R.id.red_dyed)
        val blackDyed: Button = findViewById(R.id.black_dyed)
        val playMat: Button = findViewById(R.id.play_mat)
        val cedar: Button = findViewById(R.id.cedar)
        var chosenType = "Null"

        premiumBark.setOnClickListener {
            chosenType = "Premium Bark"
        }
        specialBlend.setOnClickListener {
            chosenType = "Special Blend"
        }
        tripleGround.setOnClickListener {
            chosenType = "Triple Ground"
        }
        chocDyed.setOnClickListener {
            chosenType = "Chocolate Dyed"
        }
        redDyed.setOnClickListener {
            chosenType = "Red Dyed"
        }
        blackDyed.setOnClickListener {
            chosenType = "Black Dyed"
        }
        playMat.setOnClickListener {
            chosenType = "Play Mat"
        }
        cedar.setOnClickListener {
            chosenType = "Cedar"
        }

        nextButton.setOnClickListener {
            val i = Intent(this@MainActivity, OrderMulchActivity::class.java)
            i.putExtra("mulchTypeChosen", chosenType)
            startActivity(i)
        }
    }
}