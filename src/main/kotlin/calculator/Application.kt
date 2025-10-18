package calculator

import camp.nextstep.edu.missionutils.Console

// [feat] 기능 2-1: 커스텀 구분자를 사용하는 문자열인지 판단하는 기능
fun isUsingCustomDelimiter(calculatorInput: String): Boolean {
    return calculatorInput.startsWith("//")
}

// [feat] 기능 2-2: 커스텀 구분자를 추출하는 기능
fun splitUsingCustomDelimiter(calculatorInput: String): Pair<String, List<String>> {
    // TODO(feat): splitCalculatorInput의 크기가 2보다 작은 경우는 오류
    val splitCalculatorInput = calculatorInput.split("\\n", limit = 2)

    // TODO(feat): splitCalculatorInput[0]의 length가 3을 초과하는 경우는 오류
    val customDelimiter = splitCalculatorInput[0].substring(2)
    val numbers = splitCalculatorInput[1]

    return numbers to listOf(":", ",", customDelimiter)
}

fun main() {
    // TODO(goals): 프로그램 구현
    // TODO(feat): split() 결과에 빈 값이 포함된 경우는 오류
    // TODO(refactor): 출력 코드 중복 제거

    // [feat] 기능 0: 입력
    println("덧셈할 문자열을 입력해 주세요.")
    val calculatorInput: String = Console.readLine()

    if (isUsingCustomDelimiter(calculatorInput)) {
        // [feat] 기능 2-3: 커스텀 구분자 및 기본 구분자를 통해 문자열의 숫자를 분리하는 기능
        val (numbers, delimiters) = splitUsingCustomDelimiter(calculatorInput)
        val regex: Regex = Regex(delimiters.joinToString("|") { Regex.escape(it) })
        val splitInput: List<String> = numbers.split(regex)
        val sumOfSplitInput: Int = splitInput.sumOf { it.toInt() }

        // [feat] 기능 0: 출력
        val calculatorOutput: Int = sumOfSplitInput
        println("결과 : $calculatorOutput")

    } else {
        // [feat] 기능 1-1: 구분자(쉼표, 콜론)를 기준으로 숫자로 분리하는 기능
        val splitInput: List<String> = calculatorInput.split(Regex("[:,]"))

        // [feat] 기능 1-2: (Built-in) 분리된 숫자들의 합을 반환하는 기능
        val sumOfSplitInput = splitInput.sumOf { it.toInt() }

        // [feat] 기능 0: 출력
        val calculatorOutput: Int = sumOfSplitInput
        println("결과 : $calculatorOutput")
    }

}
