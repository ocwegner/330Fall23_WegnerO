package edu.noctrl.fall23_330.mulch_wegnero

class CubicYardMulchPricer(): MulchPricer {
    override fun calculatePrice(cubicYards: Int): Double {
        val pricePerCuYard: Double

        if (cubicYards < 3) {
            pricePerCuYard = 33.50
        } else if (cubicYards in 4..9) {
            pricePerCuYard = 31.50
        } else {
            pricePerCuYard = 29.50
        }

        return pricePerCuYard * cubicYards
    }
}