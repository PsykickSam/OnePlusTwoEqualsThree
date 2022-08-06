package com.md.sadman.oneplustwoequalsthreeremake.interpreter.node

/**
 * The single number node with the value, for equation evaluation the left
 * and right number of the binary tree is necessary so this number node is
 * helpful to save the number as a node.
 *
 * @author Md. Sadman
 *
 * @param value
 */
class NumberNode(private var value: Double) {

    fun getValue(): Double {
        return this.value
    }

    fun setValue(value: Double) {
        this.value = value
    }

    override fun toString(): String {
        return "${this.value}"
    }

}