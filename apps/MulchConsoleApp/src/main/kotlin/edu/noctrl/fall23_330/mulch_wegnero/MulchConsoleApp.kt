package edu.noctrl.fall23_330.mulch_wegnero

fun main() {
        val cubicFootPricer = CubicFootMulchPricer()
        val cubicYardsPricer = CubicYardMulchPricer()

        //ORDERS:
        val firstOrder = MulchOrder(PlantingBedDimensions(20.0, 5.0, 10.0))
        firstOrder.pricer = cubicYardsPricer
        firstOrder.printOrderDetails()

        println()

        //set values to buy in bags
        val secondOrder = MulchOrder(PlantingBedDimensions(30.0, 10.0, 5.0))
        secondOrder.addPlantingBedDimensions(PlantingBedDimensions(43.0, 14.0, 4.0))
        secondOrder.pricer = cubicFootPricer
        secondOrder.printOrderDetails()
    }

//test data set 1: 5x5x10
//test data set 2: 23x32x12, 20x15x24
//Ask about MulchPricer bulk vs bagged preference thing