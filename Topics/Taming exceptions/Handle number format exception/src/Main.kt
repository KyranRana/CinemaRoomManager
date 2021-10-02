import java.lang.Exception

fun parseCardNumber(cardNumber: String): Long {
    if (cardNumber.count { it == ' ' } != 3) {
        throw Exception("Card number must have 3 spaces!")
    }
    return cardNumber.replace(" ", "").toLong()
}