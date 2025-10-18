package calculator

import camp.nextstep.edu.missionutils.Console

fun main() {
    // [feat] 기능 0: 입력
    println("덧셈할 문자열을 입력해 주세요.")
    val calculatorInput = Console.readLine()

    try {
        // [feat] 기능 0: 출력
        val calculatorOutput: Int = calculateSum(calculatorInput)
        println("결과 : $calculatorOutput")

    } catch (e: IllegalArgumentException) {
        println("올바르게 입력해주세요: ${e.message}")
        throw e
    }

}

// [feat] 기능 2-1: 커스텀 구분자를 사용하는 문자열인지 판단하는 기능
fun isUsingCustomDelimiter(calculatorInput: String): Boolean {
    return calculatorInput.startsWith("//")
}

// [feat] 기능 2-2: 커스텀 구분자를 추출하는 기능
fun extractCustomDelimiterAndNumber(calculatorInput: String): Pair<String, List<String>> {
    val splitCalculatorInput = calculatorInput.split("\\n", limit = 2)
    require(splitCalculatorInput.size == 2) { "잘못된 형식입니다." }

    // related pre-condition: isUsingCustomDelimiter()
    val delimiterPart = splitCalculatorInput[0]
    val numbers = splitCalculatorInput[1]
    require(delimiterPart.length == 3) { "커스텀 구분자는 1글자여야 합니다." }

    val customDelimiter = splitCalculatorInput[0].substring(2)
    require(!customDelimiter[0].isDigit()) { "커스텀 구분자는 숫자가 아닌 문자여야 합니다." }

    return numbers to listOf(":", ",", customDelimiter)
}

fun splitAndSum(numbers: String, delimiters: List<String>): Int {
    val regex = Regex(delimiters.joinToString("|") { Regex.escape(it) })
    val splitInput = numbers.split(regex)

    val sumOfSplitInput = splitInput.sumOf { str ->
        require(str.isNotEmpty()) { "비어있는 값은 허용되지 않습니다." }
        val num = str.toIntOrNull() ?: throw IllegalArgumentException("숫자를 입력해주세요. (${str})")
        require(num > 0) { "입력은 양수여야 합니다." }

        num
    }

    return sumOfSplitInput
}

fun calculateSum(calculatorInput: String): Int {
    if (calculatorInput.isBlank()) {
        return 0
    }

    return if (isUsingCustomDelimiter(calculatorInput)) {
        // [feat] 기능 2-3: 커스텀 구분자 및 기본 구분자를 통해 문자열의 숫자를 분리하는 기능
        val (numbers, delimiters) = extractCustomDelimiterAndNumber(calculatorInput)
        splitAndSum(numbers, delimiters)
    } else {
        // [feat] 기능 1-1: 구분자(쉼표, 콜론)를 기준으로 숫자로 분리하는 기능
        splitAndSum(calculatorInput, listOf(":", ","))
    }
}