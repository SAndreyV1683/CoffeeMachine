import java.util.*

fun main() {
    CoffeeMachine().start()
}


class CoffeeMachine : MakeCoffee() {
    private var scanner = Scanner(System.`in`)
    private var command = ""

    fun start() {

        println("Кофемашина готова к работе")

        while (true) {
            println("Введите команду")
            command = scanner.nextLine()

            when(command.lowercase()) {
                "выключить" -> {
                    turnOff()
                    break
                }
                "наполнить" -> {
                    fill()
                }
                "статус" -> {
                    status()
                }
                "кофе" -> {
                    coffee()
                }
                else -> println("Ошибка. Введите корректную команду")
            }
        }
    }

    private fun fill() {
        water = 2000
        milk = 1000
        beans = 500
        println("Ингридиенты пополнены")
    }

    private fun status() {
        println("Ингридиентов осталось:\n$water мл воды\n$milk мл молока\n$beans гр кофе")
    }

    private fun coffee() {
        println("Введите название напитка или \"назад\" для возврата в главное меню")
        val command = scanner.nextLine()
        when (command.lowercase()) {
            CoffeeRecipes.AMERICANO.coffeeName -> {
                make(CoffeeRecipes.AMERICANO.coffeeName, water = CoffeeRecipes.AMERICANO.w, beans = CoffeeRecipes.AMERICANO.b)
            }
            CoffeeRecipes.ESPRESSO.coffeeName -> {
                make(CoffeeRecipes.ESPRESSO.coffeeName, water = CoffeeRecipes.ESPRESSO.w, beans = CoffeeRecipes.ESPRESSO.b)
            }
            CoffeeRecipes.CAPPUCCINO.coffeeName -> {
                make(CoffeeRecipes.CAPPUCCINO.coffeeName, water = CoffeeRecipes.CAPPUCCINO.w, beans = CoffeeRecipes.CAPPUCCINO.b, milk = CoffeeRecipes.CAPPUCCINO.m)
            }
            CoffeeRecipes.LATTE.coffeeName -> {
                make(CoffeeRecipes.LATTE.coffeeName, water = CoffeeRecipes.LATTE.w, beans = CoffeeRecipes.LATTE.b, milk = CoffeeRecipes.LATTE.m)
            } "назад" -> Unit
            else -> {
                println("Рецепт не найден!")
            }
        }
    }

    private fun turnOff() {
        println("До свидания!")
    }


}

abstract class MakeCoffee {
    var water = 0
    var milk = 0
    var beans = 0

    fun make (coffeeName: String, water: Int = 0, milk: Int = 0,  beans: Int = 0) {
        if (this.water >= water && this.beans >= beans && this.milk >= milk) {
            this.water -= water
            this.beans -= beans
            this.milk -= milk
            println("$coffeeName готов")
        } else {
            if (this.water < water) {
                println("Недостаточно воды!")
            } else if (this.beans < beans) {
                println("Недостаточно кофе!")
            } else if (this.milk < milk) {
                println("Недостаточно молока!")
            }
        }
    }
}

enum class CoffeeRecipes (val coffeeName: String, var w: Int = 0, var m: Int = 0, var b: Int = 0) {
    ESPRESSO("эспрессо", w = 60, b = 10),
    AMERICANO("американо", w = 120, b = 10),
    CAPPUCCINO("капучино", w = 120, m = 60, b = 20),
    LATTE("латте", w = 240, b = 20, m = 120);
}



