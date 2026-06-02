package com.example.baseconverters.model

class OctalQuestion(

    decimalValue: Int,
    toBase: Int

) : Question(decimalValue, 8, toBase) {

    override fun getQuestionText(): String {

        return Integer.toOctalString(decimalValue)
    }

    override fun getAnswer(): String {

        return when (toBase) {

            2 -> Integer.toBinaryString(decimalValue)

            10 -> decimalValue.toString()

            16 -> Integer.toHexString(decimalValue).uppercase()

            else -> ""
        }
    }
}