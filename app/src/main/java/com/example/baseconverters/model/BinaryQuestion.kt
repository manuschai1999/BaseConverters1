package com.example.baseconverters.model

class BinaryQuestion(

    decimalValue: Int,
    toBase: Int

) : Question(decimalValue, 2, toBase) {

    override fun getQuestionText(): String {

        return Integer.toBinaryString(decimalValue)
    }

    override fun getAnswer(): String {

        return when (toBase) {

            8 -> Integer.toOctalString(decimalValue)

            10 -> decimalValue.toString()

            16 -> Integer.toHexString(decimalValue).uppercase()

            else -> ""
        }
    }
}