package edu.noctrl.fall23_330.mulch_wegnero
import kotlin.math.ceil

class MulchOrder(plantingBedDimensions: PlantingBedDimensions){
    private val plantingBedDimensionsList = mutableListOf<PlantingBedDimensions>()
    init{
        plantingBedDimensionsList.add(plantingBedDimensions)
    }
    lateinit var pricer: MulchPricer

    fun addPlantingBedDimensions(plantingBedDimensions: PlantingBedDimensions) {
        plantingBedDimensionsList.add(plantingBedDimensions)
    }

    private fun calculateCubicFeet(): Int {
        val total: Double = plantingBedDimensionsList.sumOf {it.length * it.width * (it.depth/12)}
        val totalRounded = ceil(total)
        return totalRounded.toInt()
    }

    fun calculateCubicYards(): Int {
        val total: Double = plantingBedDimensionsList.sumOf {it.length * it.width * (it.depth/12)}
        val totalConverted: Double = total / 27
        val totalRounded = ceil(totalConverted)
        return totalRounded.toInt()
    }
    fun printOrderDetails(){
        for (dimensions in plantingBedDimensionsList){
            println("Planting Bed Dimensions: ${dimensions.length} x ${dimensions.width} x ${dimensions.depth}")
        }
        println("Total Cubic Yards: ${calculateCubicYards()}")
        println("Total Cubic Feet: ${calculateCubicFeet()}")
        println("Total Price: $${pricer.calculatePrice(calculateCubicYards())}")
    }
}