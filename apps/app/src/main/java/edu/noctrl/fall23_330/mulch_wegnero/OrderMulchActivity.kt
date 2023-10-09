package edu.noctrl.fall23_330.mulch_wegnero

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class OrderMulchActivity : AppCompatActivity() {
    private lateinit var address: EditText
    private lateinit var state: EditText
    private lateinit var city: EditText
    private lateinit var zipCode: EditText
    private lateinit var email: EditText
    private lateinit var phone: EditText
    private lateinit var cost: TextView
    private lateinit var tax: TextView
    private lateinit var total: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_mulch)
        val chosenType = intent.getStringExtra("mulchTypeChosen")
        val titleBox: TextView = findViewById(R.id.mulch_order)
        titleBox.text = chosenType

        val mulchPrice = pricePerYard(chosenType.toString())
        val texts = yardNumber(mulchPrice)

        val nextButton: Button = findViewById(R.id.next_button)
        nextButton.setOnClickListener {
            val addressMap = writeAddress()
            val addressText = addressMap.getValue("address")
            val stateText = addressMap.getValue("state")
            val cityText = addressMap.getValue("city")
            val zipCode = addressMap.getValue("zipCode")
            val emailText = addressMap.getValue("email")
            val phoneText = addressMap.getValue("phone")
            val price = texts.getValue("mulchPriceTotal")
            val taxes = texts.getValue("taxes")
            val total = texts.getValue("total")
            val bagNum = texts.getValue("bagNum")

            val bundle = Bundle()
            bundle.putString("chosenType", chosenType)
            bundle.putString("bagNum", bagNum.toString())
            bundle.putString("perBag", mulchPrice.toString())
            bundle.putString("address", addressText)
            bundle.putString("state", stateText)
            bundle.putString("city", cityText)
            bundle.putString("zipCode", zipCode)
            bundle.putString("email", emailText)
            bundle.putString("phone", phoneText)
            bundle.putString("price", price.toString())
            bundle.putString("taxes", taxes.toString())
            bundle.putString("total", total.toString())

            val i = Intent(this@OrderMulchActivity, OrderSummaryActivity::class.java)
            i.putExtras(bundle)
            startActivity(i)
        }
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
    private fun yardNumber(perBagPrice: Int): Map<String, Any> {
        val plusButton: Button = findViewById(R.id.plusButton)
        val minusButton: Button = findViewById(R.id.minusButton)
        val bagCountText: TextView = findViewById(R.id.bagCount)
        var bagCount = 1
        val deliveryCost: TextView = findViewById(R.id.deliveryChargeText)
        deliveryCost.text = "$0.0"
        var mulchPrice = (perBagPrice * bagCount)

        var taxes = String.format("%.2f", mulchPrice * .0625).toDouble()
        var texts = writeTextBoxes(mulchPrice, taxes)
        bagCountText.text = "1"

        plusButton.setOnClickListener {
            bagCount += 1
            bagCountText.text = bagCount.toString()
            mulchPrice = (perBagPrice * bagCount)
            taxes = String.format("%.2f", mulchPrice * .0625).toDouble()
            writeTextBoxes(mulchPrice, taxes)
        }
        minusButton.setOnClickListener {
            bagCount -= 1
            mulchPrice = (perBagPrice * bagCount)
            taxes = String.format("%.2f", mulchPrice * .0625).toDouble()
            writeTextBoxes(mulchPrice, taxes)
            bagCountText.text = bagCount.toString()
            if (bagCount < 1) {
                val text = "Must buy at least one bag!"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(this, text, duration) // in Activity
                toast.show()
                bagCount = 1
                bagCountText.text = bagCount.toString()
                writeTextBoxes(mulchPrice, taxes)
            }
        }
        texts["bagNum"] = bagCount.toString()
        return texts
    }

    //set address + contact info
    private fun writeAddress(): Map<String, String> {
        address = findViewById(R.id.street)
            val addressText = address.text.toString()

        state = findViewById(R.id.state)
            val stateText = state.text.toString()

        city = findViewById(R.id.city)
            val cityText = city.text.toString()

        zipCode = findViewById(R.id.zipCode)
            val zipCodeText = zipCode.text.toString()

        email = findViewById(R.id.emailAddress)
            val emailText = email.text.toString()

        phone = findViewById(R.id.phoneNumber)
            val phoneText = phone.text.toString()


        val shippingMap = mapOf(
            Pair("address", addressText),
            Pair("state", stateText),
            Pair("city", cityText),
            Pair("zipCode", zipCodeText),
            Pair("email", emailText),
            Pair("phone", phoneText)
        )

        return (shippingMap)
    }

    //put monetary info into text boxes
    private fun writeTextBoxes(mulchPriceTotal: Int, taxes: Double): MutableMap<String, Any> {
        cost = findViewById(R.id.mulchCostText)
        cost.text = "$$mulchPriceTotal"

        tax = findViewById(R.id.salesTaxText)
        tax.text = "$$taxes"

        total = findViewById(R.id.totalText)
        val totalCost = (mulchPriceTotal + taxes).toString()
        total.text = "$$totalCost"

        return mutableMapOf("mulchPriceTotal" to mulchPriceTotal.toString(), "taxes" to taxes.toString(),
            "total" to totalCost, "bagNum" to "Empty")
    }
}
