package com.example.baseconverters.model

open class Question(

    var decimalValue: Int,
    var fromBase: Int,
    var toBase: Int

) {

    open fun getQuestionText(): String {
        return decimalValue.toString()
    }

    open fun getAnswer(): String {
        return decimalValue.toString()
    }
}