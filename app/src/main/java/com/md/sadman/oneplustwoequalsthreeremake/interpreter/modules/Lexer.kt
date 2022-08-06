package com.md.sadman.oneplustwoequalsthreeremake.interpreter.modules

import com.md.sadman.oneplustwoequalsthreeremake.interpreter.constants.Constant
import com.md.sadman.oneplustwoequalsthreeremake.interpreter.constants.TokenTypeEnum
import java.lang.StringBuilder

/**
 * The lexer is the lexical analyzer, make token of mathematical operator
 * and numbers from the string. Tokenize all the elements either in number
 * or mathematical operator for the parser.
 *
 * @aythod Md. Sadman
 *
 * @param equation
 */
class Lexer(equation: StringBuilder) {

    private val nTokens = mutableListOf<Token>()
    private var nEquationIterator: CharIterator = equation.iterator()
    private var nCurrentChar: Char? = '\u0000'

    init {
        this.iterate()
    }

    private fun iterate() {
        if (!this.nEquationIterator.hasNext()) this.nCurrentChar = null
        else this.nCurrentChar = this.nEquationIterator.next()
    }

    private fun mMakeNumber(): Token {
        val number = StringBuilder(this.nCurrentChar.toString())
        this.iterate()

        while (this.nCurrentChar != null && Constant.DIGITS.contains(this.nCurrentChar!!)) {
            number.append(this.nCurrentChar.toString())
            this.iterate()
        }

        return Token(TokenTypeEnum.NUMBER, number.toString())
    }

    fun getTokens(): List<Token> {
        return this.nTokens
    }

    fun generateToken() {
        if (this.nCurrentChar == null) return

        if (Constant.DIGITS.contains(this.nCurrentChar!!)) {
            nTokens.add(this.mMakeNumber())
        } else {
            if (Constant.OPERATORS.contains(this.nCurrentChar!!)) {
                when {
                    this.nCurrentChar!! == Constant.OP_PLS -> {
                        nTokens.add(Token(TokenTypeEnum.PLUS))
                    }
                    this.nCurrentChar!! == Constant.OP_MIN -> {
                        nTokens.add(Token(TokenTypeEnum.MINUS))
                    }
                    this.nCurrentChar!! == Constant.OP_MUL -> {
                        nTokens.add(Token(TokenTypeEnum.MULTIPLY))
                    }
                    this.nCurrentChar!! == Constant.OP_DIV -> {
                        nTokens.add(Token(TokenTypeEnum.DIVIDE))
                    }
                }
            }

            this.iterate()
        }

        return generateToken()
    }

}