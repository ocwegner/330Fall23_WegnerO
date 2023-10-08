package edu.noctrl.fall23_330.mulch_wegnero

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged


class OrderMulchActivity : AppCompatActivity() {
    private lateinit var address: EditText
    private lateinit var state: EditText
    private lateinit var city: EditText
    private lateinit var zipCode: EditText
    private lateinit var email: EditText
    private lateinit var phone: EditText
    private lateinit var cost: TextView
    private lateinit var tax: TextView
    private lateinit var delivery: TextView
    private lateinit var total: TextView
    private var shippingCost: Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_mulch)
        val chosenType = intent.getStringExtra("mulchTypeChosen")
        val titleBox: TextView = findViewById(R.id.mulch_order)
        titleBox.text = chosenType

        val addressMap = writeAddress()
        val addressText = addressMap.getValue("address")
        val stateText = addressMap.getValue("state")
        val cityText = addressMap.getValue("city")
        val zipCode = addressMap.getValue("zipCode")
        val emailText = addressMap.getValue("email")
        val phoneText = addressMap.getValue("phone")

        val mulchPrice = pricePerYard(chosenType.toString())
        val shipping = writeShipping(zipCode)
        yardNumber(mulchPrice, shipping)

        val bundle = Bundle()
        bundle.putString("address", addressText)
        bundle.putString("state", stateText)
        bundle.putString("city", cityText)
        bundle.putString("zipCode", zipCode)
        bundle.putString("email", emailText)
        bundle.putString("phone", phoneText)
        bundle.putDouble("shipping", shipping)

        val nextButton: Button = findViewById(R.id.next_button)
        nextButton.setOnClickListener {
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
    private fun yardNumber(perBagPrice: Int, shipping: Double): Int {
        val plusButton: Button = findViewById(R.id.plusButton)
        val minusButton: Button = findViewById(R.id.minusButton)
        val bagCountText: TextView = findViewById(R.id.bagCount)
        var bagCount = 1
        bagCountText.text = "1"

        plusButton.setOnClickListener {
            bagCount += 1
            bagCountText.text = bagCount.toString()
            val mulchPrice = (perBagPrice * bagCount)
            val taxes = mulchPrice * .0625
            writeTextBoxes(mulchPrice, taxes, shipping)
        }
        minusButton.setOnClickListener {
            bagCount -= 1
            bagCountText.text = bagCount.toString()
            if (bagCount < 1) {
                val text = "Must buy at least one bag!"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(this, text, duration) // in Activity
                toast.show()
                bagCount = 1
                bagCountText.text = bagCount.toString()
            }
            val mulchPrice = (perBagPrice * bagCount)
            val taxes = mulchPrice * .0625
            writeTextBoxes(mulchPrice, taxes, shipping)

        }
        return bagCount
    }

    //set address + contact info
    private fun writeAddress(): Map<String, String> {
        var addressText = "AddressTest"
        var stateText = "StateTest"
        var cityText = "CityTest"
        var zipCodeText = "ZipTest"
        var emailText = "EmailTest"
        var phoneText = "PhoneTest"
        address = findViewById(R.id.street)
        address.doAfterTextChanged {
            addressText = address.text.toString()
        }
        state = findViewById(R.id.state)
        state.doAfterTextChanged {
            stateText = state.text.toString()
        }
        city = findViewById(R.id.city)
        city.doAfterTextChanged {
            cityText = city.text.toString()
        }
        zipCode = findViewById(R.id.zipCode)
        zipCode.doAfterTextChanged {
            zipCodeText = zipCode.text.toString()
        }
        email = findViewById(R.id.emailAddress)
        email.doAfterTextChanged {
            emailText = email.text.toString()
        }
        phone = findViewById(R.id.phoneNumber)
        phone.doAfterTextChanged {
            phoneText = phone.text.toString()
        }

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

    //get shipping cost from ZIP code
    private val zipCodeMap = mapOf(
        Pair("60540", 25.0),
        Pair("60563", 30.0),
        Pair("60564", 35.0),
        Pair("60565", 35.0),
        Pair("60187", 40.0),
        Pair("60188", 40.0),
        Pair("60189", 35.0),
        Pair("60190", 40.0)
    )

    private fun writeShipping(zipCode: String): Double {
        for (pair in zipCodeMap) {
            shippingCost = if (zipCode == pair.key) {
                pair.value
            } else 0.0
        }
        delivery = findViewById(R.id.deliveryChargeText)
        delivery.text = "$$shippingCost"
        return shippingCost
    }

    //put monetary info into text boxes
    private fun writeTextBoxes(mulchPriceTotal: Int, taxes: Double, shipping: Double) {
        cost = findViewById(R.id.mulchCostText)
        cost.text = "$$mulchPriceTotal"

        tax = findViewById(R.id.salesTaxText)
        tax.text = "$$taxes"

        total = findViewById(R.id.totalText)
        val totalCost = (mulchPriceTotal + taxes + shipping).toString()
        total.text = "$$totalCost"
    }
}
