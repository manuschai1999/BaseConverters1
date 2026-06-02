package com.example.baseconverters.model

class HexQuestion(

    decimalValue: Int,
    toBase: Int

) : Question(decimalValue, 16, toBase) {

    override fun getQuestionText(): String {

        return Integer.toHexString(decimalValue).uppercase()
    }

    override fun getAnswer(): String {

        return when (toBase) {

            2 -> Integer.toBinaryString(decimalValue)

            8 -> Integer.toOctalString(decimalValue)

            10 -> decimalValue.toString()

            else -> ""
        }
    }
}