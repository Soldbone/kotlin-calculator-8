package calculator

import camp.nextstep.edu.missionutils.Console

fun main() {
    // TODO: 프로그램 구현

    // [feat] 기능 0: 입력
    val calculatorInput: String = Console.readLine()

    // [feat] 기능 1-1: 구분자(쉼표, 콜론)를 기준으로 숫자로 분리하는 기능
    val splitInput: List<String> = calculatorInput.split(Regex("[:,]"))

    // [feat] 기능 1-2: (Built-in) 분리된 숫자들의 합을 반환하는 기능
    val sumOfSplitInput = splitInput.sumOf { it.toInt() }

    // [feat] 기능 0: 출력
    val calculatorOutput: Int = sumOfSplitInput
    println("결과 : $calculatorOutput")

}
