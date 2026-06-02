package com.example.baseconverters.model

class Player {

    var score = 0

    var lives = 3

    fun addScore(points: Int) {
        score += points
    }

    fun loseLife() {
        lives--
    }
}