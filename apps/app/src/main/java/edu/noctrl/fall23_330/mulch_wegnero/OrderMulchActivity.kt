package edu.noctrl.fall23_330.mulch_wegnero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

class OrderMulchActivity : AppCompatActivity() {
    private lateinit var layout: ConstraintLayout
    private lateinit var address: EditText
    private lateinit var state: EditText
    private lateinit var city: EditText
    private lateinit var zipCode: EditText
    private lateinit var email: EditText
    private lateinit var phone: EditText
    private lateinit var costText: EditText
    private lateinit var taxText: EditText
    private lateinit var deliveryText: EditText
    private lateinit var totalText: EditText
    private var shippingCost: Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_mulch)
        val chosenType = intent.getStringExtra("mulchTypeChosen")
        val titleBox: TextView = findViewById(R.id.mulch_order)
        titleBox.text = chosenType

        val mulchPrice = pricePerYard(chosenType.toString())
        val numOfYards = yardNumber()
        val mulchPriceTotal = mulchPrice * numOfYards
        setAddress()
        val taxes = mulchPrice * .0625
        val shipping = setShipping(zipCode.toString())
        setTextBoxes(mulchPriceTotal, taxes, shipping)
    }

    //set price per cubic yard
    private fun pricePerYard(chosenType: String): Int {
        val textBox: TextView = findViewById(R.id.textTest)
        var price = 0
        val mulchPriceMap = mapOf(
            Pair("Premium Bark Mulch", 56),
            Pair("Special Blend", 35),
            Pair("Triple Ground", 40),
            Pair("Chocolate Dyed", 38),
            Pair("Black Dyed", 38),
            Pair("Red Dyed", 38),
            Pair("Play Mat", 38),
            Pair("Cedar", 38)
        )
        for (pair in mulchPriceMap) {
            if (chosenType == pair.key) {
                price = pair.value
                val textLine = getString(R.string.price_per, price.toString())
                textBox.text = textLine
            }
        }
        return price
    }

    //get number of cubic yards from buttons
    private fun yardNumber(): Int {
        val plusButton: Button = findViewById(R.id.plusButton)
        val minusButton: Button = findViewById(R.id.minusButton)
        val bagCountText: TextView = findViewById(R.id.bagCount)
        var bagCount = 1
        bagCountText.text = "1"

        plusButton.setOnClickListener {
            bagCount += 1
            bagCountText.text = bagCount.toString()
        }
        minusButton.setOnClickListener {
            bagCount -= 1
            if (bagCount < 1) {
                val text = "Must buy at least one bag!"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(this, text, duration) // in Activity
                toast.show()
                bagCount = 1
                bagCountText.text = bagCount.toString()
            }
        }
        return bagCount
    }

    //set address + contact info
    private fun setAddress() {
        address = findViewById(R.id.street)
        state = findViewById(R.id.state)
        city = findViewById(R.id.city)
        zipCode = findViewById(R.id.zipCode)
        email = findViewById(R.id.emailAddress)
        phone = findViewById(R.id.phoneNumber)
    }

    //get shipping cost from ZIP code
    private fun setShipping(zipCode: String): Double {
        val intZipCode = zipCode.toInt()
        val zipCodeMap = mapOf(
            Pair(60540, 25.0),
            Pair(60563, 30.0),
            Pair(60564, 35.0),
            Pair(60565, 35.0),
            Pair(60187, 40.0),
            Pair(60188, 40.0),
            Pair(60189, 35.0),
            Pair(60190, 40.0)
        )
        for (pair in zipCodeMap) {
            if (intZipCode == pair.key){
                shippingCost = pair.value
            }
        }
        if (shippingCost == 0.0){
            layout = findViewById(R.id.bottomLayout)
            val snackBar = Snackbar.make(layout, "Delivery not available for your area; pickup required.", Snackbar.LENGTH_LONG)
            snackBar.show()
        }
        return shippingCost
    }

    //put monetary info into text boxes
    private fun setTextBoxes(mulchPriceTotal: Int, taxes: Double, shipping: Double) {
        costText = findViewById(R.id.mulchCostText)
        costText.setText(mulchPriceTotal)

        taxText = findViewById(R.id.salesTaxText)
        taxText.setText(taxes.toString())

        deliveryText = findViewById(R.id.deliveryChargeText)
        deliveryText.setText(shipping.toString())

        totalText = findViewById(R.id.totalText)
        val totalCost = mulchPriceTotal + taxes + shipping
        totalText.setText(totalCost.toString())
    }
}