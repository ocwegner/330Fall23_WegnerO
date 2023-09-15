package edu.noctrl.fall23_330.mulch_wegnero

import kotlin.math.ceil

class CubicFootMulchPricer : MulchPricer {
    override fun calculatePrice(cubicYards: Int): Double {
        val pricePerCuFoot: Double
        val cubicFeet : Double = (cubicYards * 27.0)/2
        val cubicFeetBags : Double = ceil(cubicFeet)

        if (cubicFeetBags < 3) {
            pricePerCuFoot = 3.97
        }
        else if (cubicFeetBags in 4.0..9.0) {
            pricePerCuFoot = 3.47
        }
        else {
            pricePerCuFoot = 2.97
        }

        return pricePerCuFoot * cubicFeetBags
    }
}