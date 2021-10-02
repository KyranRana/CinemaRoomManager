package cinema

fun main() {
    println("Enter the number of rows:")
    val numberOfRows = readLine()!!.toInt()

    println("Enter the number of seats in each row:")
    val numberOfSeats = readLine()!!.toInt()

    println()

    val bookedSeats = mutableSetOf<Int>()
    var income = 0

    while (true) {
        showInstructions()

        when (readLine()!!.toInt()) {
            1 -> printScreenRoom(numberOfRows, numberOfSeats, bookedSeats)
            2 -> {
                while (true) {
                    println()

                    println("Enter a row number:")
                    val row = readLine()!!.toInt()

                    println("Enter a seat number in that row:")
                    val seat = readLine()!!.toInt()

                    if (row > numberOfRows || seat > numberOfSeats) {
                        println()
                        println("Wrong input!")
                        continue
                    }

                    val seatIndex = row * numberOfRows + seat
                    if (seatIndex in bookedSeats) {
                        println()
                        println("That ticket has already been purchased!")
                        continue
                    }

                    val ticketPrice = findTicketPrice(numberOfRows, numberOfSeats, row)

                    println()
                    println("Ticket price: $$ticketPrice\n")

                    bookedSeats += seatIndex
                    income += ticketPrice
                    break
                }
            }
            3 -> printStatistics(numberOfRows, numberOfSeats, income, bookedSeats.size)
            else -> break
        }
    }
}

fun showInstructions() =
    println("""
        1. Show the seats
        2. Buy a ticket
        3. Statistics
        0. Exit
    """.trimIndent())

fun findTicketPrice(rows: Int, seats: Int, row: Int): Int =
    if (rows * seats <= 60 || row <= rows / 2) 10 else 8

fun printScreenRoom(rows: Int, seats: Int, bookedSeats: MutableSet<Int>) {
    println()

    println("Cinema:")
    println("  ${(1..seats).joinToString(" ")}")

    for (i in 1..rows) {
        print("$i ")
        for (j in 1..seats) {
            print("${if (i * rows + j in bookedSeats) 'B' else 'S'} ")
        }
        println()
    }

    println()
}

fun printStatistics(rows: Int, seats: Int, income: Int, purchased: Int) {
    println()

    val totalSeats = rows * seats
    val percentage = (purchased.toDouble() / totalSeats) * 100
    val totalIncome = if (totalSeats <= 60) {
        totalSeats * 10
    } else {
        val mid = rows / 2
        mid * seats * 10 + (rows - mid) * seats * 8
    }

    println("Number of purchased tickets: $purchased")
    println("Percentage: %.2f%%".format(percentage))
    println("Current income: $$income")
    println("Total income: $$totalIncome")
}