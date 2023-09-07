package example.myapp
import java.lang.Math.PI

fun buildAquarium(){
    val aquarium6 = Aquarium(length = 25, width = 25, height = 40)
    aquarium6.printSize()

    val myTower = Aquarium.TowerTank(diameter = 25, height = 40)
    myTower.printSize()
}

fun makeFish(){
    val shark = Shark()
    val pleco = Plecostomus()

    println("Shark: ${shark.color}")
    shark.eat()
    println("Plecostomus: ${pleco.color}")
    pleco.eat()
}
fun main(){
    //buildAquarium()
    makeFish()
}