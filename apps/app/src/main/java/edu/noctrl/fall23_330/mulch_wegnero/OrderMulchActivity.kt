package edu.noctrl.fall23_330.mulch_wegnero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class OrderMulchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_mulch)
        val chosenType = intent.getStringExtra("mulchTypeChosen")
        val titleBox: TextView = findViewById(R.id.mulch_order)
        titleBox.text = chosenType

        //set price per cubic yard
        val textBox: TextView = findViewById(R.id.textTest)
        val mulchPriceMap = mapOf(Pair("Premium Bark Mulch", 56), Pair("Special Blend", 35),
            Pair("Triple Ground", 40), Pair("Chocolate Dyed", 38), Pair("Black Dyed", 38), Pair("Red Dyed", 38),
            Pair("Play Mat", 38), Pair("Cedar", 38))
        for (pair in mulchPriceMap){
            if (chosenType.equals(pair.key)){
                val price = pair.value
                textBox.text = "$$price per cubic yard"
            }
        }

        //get number of cubic yards from buttons
        val plusButton: Button = findViewById(R.id.plusButton)
        val minusButton: Button = findViewById(R.id.minusButton)
        val bagCountText: TextView = findViewById(R.id.bagCount)
        var bagCount = 1
        bagCountText.text = "1"

        plusButton.setOnClickListener {
            bagCount += 1
            bagCountText.text = bagCount.toString()
        }
        minusButton.setOnClickListener{
            bagCount -= 1
            if (bagCount < 1){
                val text = "Must buy at least one bag!"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(this, text, duration) // in Activity
                toast.show()
                bagCount = 1
                bagCountText.text = bagCount.toString()
            }
        }


        //set address + contact info

        //get shipping cost from ZIP code

        //put monetary info into text boxes

    }
}