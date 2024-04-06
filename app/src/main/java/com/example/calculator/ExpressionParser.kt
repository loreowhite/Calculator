class ExpressionParser {
    fun evaluate(expression: String): Double {
        try {
            return evaluatePostfix(expression)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return Double.NaN
    }


    private fun evaluatePostfix(value: String): Double {

        val split = value.split(" ")
        val num1 = split[0].toDouble()
        val num2 = split[2].toDouble()
        val oper = split[1]
        return when (oper) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "*" -> num1 * num2
            "/" -> if (num2 == 0.0)  Double.NaN  else num1/num2

            else -> throw IllegalArgumentException("Invalid operator: $oper")
        }
    }
}
