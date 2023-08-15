import java.util.*

fun main(args: Array<String>) {
    CoffeeMachine().start()
}


class CoffeeMachine {
    private var scanner = Scanner(System.`in`)
    var water = 0
    var milk = 0
    var beans = 0
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
                CoffeeRecipes.AMERICANO.make(this)
            }
            CoffeeRecipes.ESPRESSO.coffeeName -> {
                CoffeeRecipes.ESPRESSO.make(this)
            }
            CoffeeRecipes.CAPPUCCINO.coffeeName -> {
                CoffeeRecipes.CAPPUCCINO.make(this)
            }
            CoffeeRecipes.LATTE.coffeeName -> {
                CoffeeRecipes.LATTE.make(this)
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

interface MakeCoffee {
    fun make(coffeeMachine: CoffeeMachine)
}

enum class CoffeeRecipes (val coffeeName: String, var water: Int = 0, var milk: Int = 0, var beans: Int = 0): MakeCoffee{
    ESPRESSO("эспрессо", water = 60, beans = 10) {
        override fun make(coffeeMachine: CoffeeMachine) {
            if (coffeeMachine.water >= ESPRESSO.water && coffeeMachine.beans >= ESPRESSO.beans) {
                coffeeMachine.water -= ESPRESSO.water
                coffeeMachine.beans -= ESPRESSO.beans
                println("${ESPRESSO.coffeeName} готов")
            } else {
                if (coffeeMachine.water < ESPRESSO.water) {
                    println("Недостаточно воды!")
                }
                if (coffeeMachine.beans < ESPRESSO.beans) {
                    println("Недостаточно кофе!")
                }
            }
        }
    },
    AMERICANO("американо", water = 120, beans = 10) {
        override fun make(coffeeMachine: CoffeeMachine) {
            if (coffeeMachine.water >= AMERICANO.water && coffeeMachine.beans >= AMERICANO.beans) {
                coffeeMachine.water -= AMERICANO.water
                coffeeMachine.beans -= AMERICANO.beans
                println("${AMERICANO.coffeeName} готов")
            } else {
                if (coffeeMachine.water < AMERICANO.water) {
                    println("Недостаточно воды!")
                }
                if (coffeeMachine.beans < AMERICANO.beans) {
                    println("Недостаточно кофе!")
                }
            }
        }
    },
    CAPPUCCINO("капучино", water = 120, milk = 60, beans = 20) {
        override fun make(coffeeMachine: CoffeeMachine) {
            if (coffeeMachine.water >= CAPPUCCINO.water && coffeeMachine.beans >= CAPPUCCINO.beans && coffeeMachine.milk >= CAPPUCCINO.milk) {
                coffeeMachine.water -= CAPPUCCINO.water
                coffeeMachine.beans -= CAPPUCCINO.beans
                coffeeMachine.milk -= CAPPUCCINO.milk
                println("${CAPPUCCINO.coffeeName} готов")
            } else {
                if (coffeeMachine.water < CAPPUCCINO.water) {
                    println("Недостаточно воды!")
                }
                if (coffeeMachine.beans < CAPPUCCINO.beans) {
                    println("Недостаточно кофе!")
                }
                if (coffeeMachine.milk < CAPPUCCINO.milk) {
                    println("Недостаточно молока!")
                }
            }
        }
    },
    LATTE("латте", water = 240, beans = 20, milk = 120) {
        override fun make(coffeeMachine: CoffeeMachine) {
            if (coffeeMachine.water >= LATTE.water && coffeeMachine.beans >= LATTE.beans && coffeeMachine.milk >= LATTE.milk) {
                coffeeMachine.water -= LATTE.water
                coffeeMachine.beans -= LATTE.beans
                coffeeMachine.milk -= LATTE.milk
                println("${LATTE.coffeeName} готов")
            } else {
                if (coffeeMachine.water < LATTE.water) {
                    println("Недостаточно воды!")
                }
                if (coffeeMachine.beans < LATTE.beans) {
                    println("Недостаточно кофе!")
                }
                if (coffeeMachine.milk < LATTE.milk) {
                    println("Недостаточно молока!")
                }
            }
        }
    };
}



