package com.md.sadman.oneplustwoequalsthreeremake.interpreter.node

import com.md.sadman.oneplustwoequalsthreeremake.interpreter.constants.Constant
import com.md.sadman.oneplustwoequalsthreeremake.interpreter.constants.MathOperatorEnum

/**
 * This is the base equation node form factor, contains number, left number item (A),
 * right number item (B), left equation node (A), right equation node (B) and operator
 * (Math Operator). It is used for creating equation of number and node with operator
 * or number only formation equation to evaluate basic mathematical operation. This is
 * basically the main structure of the binary tree.
 *
 * @author Md. Sadman
 *
 * @param number
 * @param itemA
 * @param itemB
 * @param nodeA
 * @param nodeB
 * @param mathOperatorEnum
 */
class EquationNode(private val number: NumberNode?, private val itemA: NumberNode?, private val itemB: NumberNode?, private val nodeA: EquationNode?, private val nodeB: EquationNode?, private val mathOperatorEnum: MathOperatorEnum?) {

    fun getNumber(): NumberNode? {
        return this.number
    }

    fun getItemA(): NumberNode? {
        return this.itemA
    }

    fun getItemB(): NumberNode? {
        return this.itemB
    }

    fun getNodeA(): EquationNode? {
        return this.nodeA
    }

    fun getNodeB(): EquationNode? {
        return this.nodeB
    }

    fun getOperator(): MathOperatorEnum? {
        return mathOperatorEnum;
    }

    override fun toString(): String {
        var tOperator = ""

        when (mathOperatorEnum) {
            MathOperatorEnum.PLUS -> {
                tOperator = Constant.OP_PLS.toString()
            }
            MathOperatorEnum.MINUS -> {
                tOperator = Constant.OP_MIN.toString()
            }
            MathOperatorEnum.MULTIPLY -> {
                tOperator = Constant.OP_MUL.toString()
            }
            MathOperatorEnum.DIVIDE -> {
                tOperator = Constant.OP_DIV.toString()
            }
            null -> {
                tOperator = Constant.OP_NONE.toString()
            }
        }

        if (number != null)
            return number.toString()

        return "(${if (this.itemA == null) this.nodeA.toString() else this.itemA.toString()} ${tOperator} ${if (this.itemB == null) {
            if (mathOperatorEnum == MathOperatorEnum.MULTIPLY || mathOperatorEnum == MathOperatorEnum.DIVIDE) {
                this.nodeA.toString()
            } else {
                this.nodeB.toString()
            }
        } else this.itemB.toString()})"
    }
}