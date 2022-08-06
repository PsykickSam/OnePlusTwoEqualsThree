package com.md.sadman.oneplustwoequalsthreeremake.interpreter.modules

import com.md.sadman.oneplustwoequalsthreeremake.interpreter.constants.MathOperatorEnum
import com.md.sadman.oneplustwoequalsthreeremake.interpreter.node.EquationNode
import com.md.sadman.oneplustwoequalsthreeremake.interpreter.node.NumberNode

/**
 * Interpreter interprets the parsed element evaluated from parser.
 * It traversed the whole three and perform the operator action
 * between the numbers and finally return the result generate
 * from the interpreter.
 *
 * @author Md. Sadman
 */
class Interpreter {

    companion object {
        private fun init(): Interpreter {
            return Interpreter()
        }

        fun interpret(ast: EquationNode?): Double {
            return init().mTraverseTree(ast)
        }
    }

    private fun mTraverseTree(ast: EquationNode?): Double {
        if (ast == null) return 0.0

        if (ast.getNodeA() != null && ast.getNodeB() != null) {
            return this.mCalculateData(ast.getNumber(), mTraverseTree(ast.getNodeA()), mTraverseTree(ast.getNodeB()), ast.getOperator())
        }

        if (ast.getNodeA() != null) {
            return this.mCalculateData(ast.getNumber(), mTraverseTree(ast.getNodeA()), ast.getItemB(), ast.getOperator())
        }

        if (ast.getNodeB() != null) {
            return this.mCalculateData(ast.getNumber(), ast.getItemA(), mTraverseTree(ast.getNodeB()), ast.getOperator())
        }

        return this.mCalculateData(ast.getNumber(), ast.getItemA(), ast.getItemB(), ast.getOperator())
    }

    private fun mCalculateData(number: NumberNode?, left: NumberNode?, right: NumberNode?, mathOperatorEnum: MathOperatorEnum?): Double {
        return this.mCalculateData(number, if (mathOperatorEnum != null) left!!.getValue() else 0.0, if (mathOperatorEnum != null) right!!.getValue() else 0.0, mathOperatorEnum)
    }

    private fun mCalculateData(number: NumberNode?, left: Double, right: NumberNode?, mathOperatorEnum: MathOperatorEnum?): Double {
        return this.mCalculateData(number, left, if (mathOperatorEnum != null) right!!.getValue() else 0.0, mathOperatorEnum)
    }

    private fun mCalculateData(number: NumberNode?, left: NumberNode?, right: Double, mathOperatorEnum: MathOperatorEnum?): Double {
        return this.mCalculateData(number, if (mathOperatorEnum != null) left!!.getValue() else 0.0, right, mathOperatorEnum)
    }

    private fun mCalculateData(number: NumberNode?, left: Double, right: Double, mathOperatorEnum: MathOperatorEnum?): Double {
        if (mathOperatorEnum != null) {
            return when (mathOperatorEnum) {
                MathOperatorEnum.PLUS -> {
                    (left + right)
                }
                MathOperatorEnum.MINUS -> {
                    (left - right)
                }
                MathOperatorEnum.MULTIPLY -> {
                    (left * right)
                }
                MathOperatorEnum.DIVIDE -> {
                    (left / right)
                }
            }
        } else {
            return number!!.getValue()
        }
    }

}