package edu.noctrl.fall23_330.mulch_wegnero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

class OrderSummaryActivity : AppCompatActivity() {
    private lateinit var layout: ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_summary)
        val bundle = intent.extras
        val shipping = bundle!!.getDouble("shipping", 0.0)
        val address = bundle.getString("address", "didn't work")

        val addressBox: TextView = findViewById(R.id.textViewforString)
        addressBox.text = address

        isDeliverable(shipping)
    }
    private fun isDeliverable(shippingCost:Double){
        if (shippingCost == 0.0){
            layout = findViewById(R.id.bottomLayout)
            val snackBar = Snackbar.make(layout, "Delivery not available for your area; pickup required.", Snackbar.LENGTH_LONG)
            snackBar.show()
        }
        val textBoxForDisplay : TextView = findViewById(R.id.newTextView)
        textBoxForDisplay.text = shippingCost.toString()
    }
}