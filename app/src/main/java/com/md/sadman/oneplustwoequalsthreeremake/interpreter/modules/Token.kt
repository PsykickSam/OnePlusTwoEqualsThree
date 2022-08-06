package com.md.sadman.oneplustwoequalsthreeremake.interpreter.modules

import com.md.sadman.oneplustwoequalsthreeremake.interpreter.constants.TokenTypeEnum

/**
 * Token is the model of the lexer token, containing the
 * token type attribute along with a value.
 *
 * @author Md. Sadman
 *
 * @param typeEnum
 * @param value
 */
class Token(private var typeEnum: TokenTypeEnum, private var value: String? = "") {

    private val nTypeEnum: TokenTypeEnum = typeEnum
    private val nValue: String? = value

    fun getType(): TokenTypeEnum {
        return nTypeEnum
    }

    fun getValue(): String? {
        return nValue
    }

    override fun toString(): String {
        return typeEnum.name + "(" + value + ")"
    }

}