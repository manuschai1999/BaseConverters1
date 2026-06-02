package com.example.baseconverters.model

class GameManager {

    var player = Player()

    var currentQuestion: Question? = null

    fun generateQuestion(): Question {

        val decimal = (1..255).random()

        val fromBase = listOf(2, 8, 16).random()

        val toBase = listOf(2, 8, 10, 16)
            .filter { it != fromBase }
            .random()

        currentQuestion = when (fromBase) {

            2 -> BinaryQuestion(decimal, toBase)

            8 -> OctalQuestion(decimal, toBase)

            else -> HexQuestion(decimal, toBase)
        }

        return currentQuestion!!
    }

    fun checkAnswer(answer: String): Boolean {

        return answer.uppercase() ==
                currentQuestion?.getAnswer()?.uppercase()
    }
}