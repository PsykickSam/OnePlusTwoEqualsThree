package com.md.sadman.oneplustwoequalsthreeremake.interpreter.modules

import com.md.sadman.oneplustwoequalsthreeremake.interpreter.constants.MathOperatorEnum
import com.md.sadman.oneplustwoequalsthreeremake.interpreter.error.InvalidSyntaxError
import com.md.sadman.oneplustwoequalsthreeremake.interpreter.node.NumberNode
import com.md.sadman.oneplustwoequalsthreeremake.interpreter.node.EquationNode
import com.md.sadman.oneplustwoequalsthreeremake.interpreter.constants.TokenTypeEnum
import java.lang.Exception

/**
 * Token parser is parsing all the tokens in to many levels,
 * factor: The lower level grammar rule contains only numbers of the binary equation
 * term: The next level of factor, the top level operation evaluation multiplication
 * and division has done here,
 * expression: The next level of term, mathematical operator evaluation rule addition
 * and subtraction
 *
 * The token flow from the expression -> term -> factor, and parse the data into
 * equation node, a binary tree structure to perform mathematical calculation
 *
 * @authod Md. Sadman
 *
 * @param tokens
 */
class Parser(tokens: List<Token>?) {

    private val nTokensIterator = tokens!!.listIterator()
    private var nCurrentToken: Token? = null

    init {
        this.iterator()
    }

    private fun iterator() {
        try {
            this.nCurrentToken = this.nTokensIterator.next()
        } catch (ex: Exception) {
            this.nCurrentToken = null
        }
    }

    private fun factor(): NumberNode? {
        var token = this.nCurrentToken

        if (token?.getType()?.equals(TokenTypeEnum.NUMBER) == true) {
            this.iterator()
            return NumberNode(token.getValue()!!.toDouble())
        } else if (token?.getType()?.equals(TokenTypeEnum.MINUS) == true) {
            this.iterator()
            token = this.nCurrentToken

            this.iterator()
            return NumberNode(token!!.getValue()!!.toDouble() * -1)
        }

        this.raiseException()
        return null
    }

    private fun term(): EquationNode {
        val tNumberNode: NumberNode? = this.factor()
        var tNode = EquationNode(tNumberNode, null, null, null, null, null)

        while (this.nCurrentToken != null && listOf(TokenTypeEnum.MULTIPLY, TokenTypeEnum.DIVIDE).contains(this.nCurrentToken!!.getType())) {
            if (this.nCurrentToken!!.getType() == TokenTypeEnum.MULTIPLY) {
                this.iterator()

                if (tNode.getNumber() != null) {
                    tNode = EquationNode(null, tNode.getNumber(), this.factor(), null, null, MathOperatorEnum.MULTIPLY)
                } else {
                    tNode = EquationNode(null, null, this.factor(), tNode, null, MathOperatorEnum.MULTIPLY)
                }
            } else if (this.nCurrentToken!!.getType() == TokenTypeEnum.DIVIDE) {
                this.iterator()

                if (tNode.getNumber() != null) {
                    tNode = EquationNode(null, tNode.getNumber(), this.factor(), null, null, MathOperatorEnum.DIVIDE)
                } else {
                    tNode = EquationNode(null, null, this.factor(), tNode, null, MathOperatorEnum.DIVIDE)
                }
            }
        }

        return tNode
    }

    private fun expression(): EquationNode {
        val tTermNode: EquationNode = this.term()
        var tNode = tTermNode

        while (this.nCurrentToken != null && (this.nCurrentToken!!.getType() == TokenTypeEnum.PLUS || this.nCurrentToken!!.getType() == TokenTypeEnum.MINUS)) {
            if (this.nCurrentToken!!.getType() == TokenTypeEnum.PLUS) {
                this.iterator()

                if (tNode.getNumber() != null) {
                    val currentTerm = this.term()

                    if (currentTerm.getNumber() != null) {
                        tNode = EquationNode(null, tNode.getNumber(), currentTerm.getNumber(), null, null, MathOperatorEnum.PLUS)
                    } else {
                        tNode = EquationNode(null, tNode.getNumber(), null, null, currentTerm, MathOperatorEnum.PLUS)
                    }
                } else {
                    tNode = EquationNode(null, null, null, tNode, this.term(), MathOperatorEnum.PLUS)
                }
            } else if (this.nCurrentToken!!.getType() == TokenTypeEnum.MINUS) {
                this.iterator()

                if (tNode.getNumber() != null) {
                    val currentTerm = this.term()

                    if (currentTerm.getNumber() != null) {
                        tNode = EquationNode(null, tNode.getNumber(), currentTerm.getNumber(), null, null, MathOperatorEnum.MINUS)
                    } else {
                        tNode = EquationNode(null, tNode.getNumber(), null, null, currentTerm, MathOperatorEnum.MINUS)
                    }
                } else {
                    tNode = EquationNode(null, null, null, tNode, this.term(), MathOperatorEnum.MINUS)
                }
            }
        }

        return tNode
    }

    private fun raiseException() {
        throw InvalidSyntaxError()
    }

    fun parse(): EquationNode? {
        if (this.nCurrentToken == null)
            return null

        val result = this.expression()

        if (this.nCurrentToken != null)
            this.raiseException()

        return result
    }

}