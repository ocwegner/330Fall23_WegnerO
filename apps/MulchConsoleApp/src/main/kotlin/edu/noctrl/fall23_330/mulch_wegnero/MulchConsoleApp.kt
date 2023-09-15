package edu.noctrl.fall23_330.mulch_wegnero

fun main() {
        val cubicFootPricer = CubicFootMulchPricer()

        //ORDERS:
        val firstOrder = MulchOrder(PlantingBedDimensions(20.0, 5.0, 10.0))
        val placeholder = -1.0
        firstOrder.printOrderDetails(placeholder)

        println()

        //set values to buy in bags
        val secondOrder = MulchOrder(PlantingBedDimensions(30.0, 10.0, 5.0))
        secondOrder.addPlantingBedDimensions(PlantingBedDimensions(43.0, 14.0, 4.0))
        val footPrice = cubicFootPricer.calculatePrice(cubicYards = secondOrder.calculateCubicYards())
        secondOrder.printOrderDetails(footPrice)
    }

//test data set 1: 5x5x10
//test data set 2: 23x32x12, 20x15x24
//Ask about MulchPricer bulk vs bagged preference thing