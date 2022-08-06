package com.md.sadman.oneplustwoequalsthreeremake.lib

import com.md.sadman.oneplustwoequalsthreeremake.interpreter.constants.Constant
import com.md.sadman.oneplustwoequalsthreeremake.interpreter.modules.Interpreter
import com.md.sadman.oneplustwoequalsthreeremake.interpreter.modules.Lexer
import com.md.sadman.oneplustwoequalsthreeremake.interpreter.modules.Parser
import com.md.sadman.oneplustwoequalsthreeremake.model.EquationData
import java.lang.StringBuilder

/**
 * Equation holds the base equation generation logic, It is crating equation automatically
 * and return a list of equations and answers
 *
 * @author Md. Sadman
 */
class Equation {

    companion object {
        private val ANSWER_BETWEEN = listOf(1, 2, 3)
        private var currentResult = -1

        fun generateEquation(equationSize: Int, totalEquation: Int): List<EquationData> {
            val tEquationList = mutableListOf<EquationData>()

            while (true) {
                val tNumberList = mutableListOf<Int>()
                val tEquation = StringBuilder()
                var j = 0

                for (i in 0 until equationSize) {
                    tNumberList.add((1..3).random())
                }

                for (i in 0 until 2 * equationSize - 1) {
                    if (i % 2 == 0) {
                        tEquation.append(tNumberList[j].toString())
                        j++
                    } else {
                        tEquation.append(Constant.OPERATORS[(0 until Constant.OPERATORS.length - 1).random()])
                    }
                }

                if (this.validateEquation(tEquation)) {
                    val data = EquationData(tEquation.toString(), currentResult)
                    if (!tEquationList.any { it.equation.equals(data.equation)}) {
                        tEquationList.add(data)
                    }
                }

                if (totalEquation == tEquationList.size) {
                    break
                }
            }

            return tEquationList.toList()
        }

        private fun validateEquation(tEquation: StringBuilder): Boolean {
            val lexer = Lexer(StringBuilder(tEquation.toString()))
            lexer.generateToken()

            val parser = Parser(lexer.getTokens())
            val ast = parser.parse()

            val result = Interpreter.interpret(ast)
            this.validResult(result)

            if (result % 1 == 0.0 && ANSWER_BETWEEN.contains(result.toInt())) {
                return true
            }

            return false
        }

        private fun validResult(result: Double) {
            currentResult = result.toInt()
        }
    }
}