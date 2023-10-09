package edu.noctrl.fall23_330.mulch_wegnero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

class OrderSummaryActivity : AppCompatActivity() {
    private lateinit var layout: ConstraintLayout
    private lateinit var titleBox: TextView
    private lateinit var mainBox: TextView
    private lateinit var street: TextView
    private lateinit var citystate: TextView
    private lateinit var zipCode: TextView
    private lateinit var emailBox: TextView
    private lateinit var phoneBox: TextView
    private lateinit var delivery: TextView
    private lateinit var priceBox: TextView
    private lateinit var taxBox: TextView
    private lateinit var totalBox: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_summary)
        val bundle = intent.extras
        val chosenType = bundle?.getString("chosenType", "didn't work")
        val bagNum = bundle?.getString("bagNum", "didn't work")
        val perBag = bundle?.getString("perBag", "didn't work")
        val address = bundle?.getString("address", "didn't work")
        val city = bundle?.getString("city", "didn't work")
        val state = bundle?.getString("state", "didn't work")
        val zip = bundle?.getString("zipCode", "0")
        val email = bundle?.getString("email", "didn't work")
        val phone = bundle?.getString("phone", "didn't work")
        val price = bundle?.getString("price", "didn't work")
        val taxes = bundle?.getString("taxes", "didn't work")
        val total = bundle?.getString("total", "didn't work")

        titleBox = findViewById(R.id.mulch_order)
        titleBox.text = "$chosenType - $bagNum yard(s) \n $$perBag per yard"

        mainBox = findViewById(R.id.MainText)
        mainBox.text = "Delivering $bagNum cubic yards of \n $chosenType to:"



        val shippingCost = writeShipping(zip.toString())
        isDeliverable(shippingCost)

        setBoxes(address.toString(), city.toString(), state.toString(), zip.toString(),
            email.toString(), phone.toString(), price.toString(), taxes.toString(), shippingCost.toString(), total.toString())

    }

    //get shipping cost from ZIP code
    private val zipCodeMap = mapOf(
        Pair(60540, 25.0),
        Pair(60563, 30.0),
        Pair(60564, 35.0),
        Pair(60565, 35.0),
        Pair(60187, 40.0),
        Pair(60188, 40.0),
        Pair(60189, 35.0),
        Pair(60190, 40.0)
    )
    private fun writeShipping(zipCode: String): Double {
        var shippingCost = 0.0
        for (pair in zipCodeMap) {
            if (zipCode.toInt() == pair.key) {
                shippingCost = pair.value
            }
        }
        delivery = findViewById(R.id.deliveryChargeText)
        delivery.text = "$$shippingCost"
        return shippingCost
    }
    private fun isDeliverable(shippingCost:Double){
        if (shippingCost == 0.0){
            layout = findViewById(R.id.bottomLayout)
            val snackBar = Snackbar.make(layout, "Delivery not available for your area; pickup required.", Snackbar.LENGTH_LONG)
            snackBar.show()
        }
    }
    private fun setBoxes(address:String, city:String, state:String, zip:String,
                         email:String, phone:String, price:String, taxes: String, shipping:String, total:String) {
        street = findViewById(R.id.AddressBox)
        street.text = address
        citystate = findViewById(R.id.CityStateBox)
        citystate.text = "$city $state"
        zipCode = findViewById(R.id.ZipCodeBox)
        zipCode.text = zip
        emailBox = findViewById(R.id.EmailBox)
        emailBox.text = email
        phoneBox = findViewById(R.id.PhoneBox)
        phoneBox.text = phone
        priceBox = findViewById(R.id.mulchCostText)
        priceBox.text = "$$price"
        taxBox = findViewById(R.id.salesTaxText)
        taxBox.text = "$$taxes"
        totalBox = findViewById(R.id.totalText)
        val trueTotal = total.toDouble() + shipping.toDouble()
        totalBox.text = "$" + trueTotal.toString()
    }
}