package com.example.baseconverters

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.baseconverters.model.GameManager
import android.view.View
import android.widget.LinearLayout
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    private lateinit var gameManager: GameManager
    private lateinit var btnStart: Button
    private lateinit var gameLayout: LinearLayout
    private lateinit var txtTitle: TextView

    private lateinit var txtQuestion: TextView
    private lateinit var txtScore: TextView
    private lateinit var txtLives: TextView
    private lateinit var txtTimer: TextView

    private lateinit var txtInstruction: TextView

    private lateinit var edtAnswer: EditText
    private lateinit var btnSubmit: Button
    private lateinit var btnRestart: Button
    private lateinit var txtGameOver: TextView
    private lateinit var imgLogo: ImageView

    private var questionTime = 60000L

    private var timer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart = findViewById(R.id.btnStart)
        gameLayout = findViewById(R.id.gameLayout)
        txtTitle = findViewById(R.id.txtTitle)
        txtQuestion = findViewById(R.id.txtQuestion)
        txtScore = findViewById(R.id.txtScore)
        txtLives = findViewById(R.id.txtLives)
        txtTimer = findViewById(R.id.txtTimer)
        txtInstruction = findViewById(R.id.txtInstruction)

        edtAnswer = findViewById(R.id.edtAnswer)
        btnSubmit = findViewById(R.id.btnSubmit)
        btnRestart = findViewById(R.id.btnRestart)
        txtGameOver = findViewById(R.id.txtGameOver)
        imgLogo = findViewById(R.id.imgLogo)

        gameManager = GameManager()

        loadQuestion()

        btnSubmit.setOnClickListener {

            val answer = edtAnswer.text.toString()

            if (gameManager.checkAnswer(answer)) {

                gameManager.player.addScore(10)

                if (questionTime > 15000) {
                    questionTime -= 3000
                }

                Toast.makeText(
                    this,
                    "Correct",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                gameManager.player.loseLife()

                Toast.makeText(
                    this,
                    "Wrong",
                    Toast.LENGTH_SHORT
                ).show()
            }

            updateUI()

            if (gameManager.player.lives <= 0) {

                Toast.makeText(
                    this,
                    "Game Over",
                    Toast.LENGTH_LONG
                ).show()

                showGameOver()
            }

        }
        btnRestart.setOnClickListener {

            recreate()
        }
        btnStart.setOnClickListener {

            btnStart.visibility = View.GONE
            txtTitle.visibility = View.GONE
            imgLogo.visibility = View.GONE

            gameLayout.visibility = View.VISIBLE

            loadQuestion()
        }
    }

    private fun loadQuestion() {

        timer?.cancel()

        val question = gameManager.generateQuestion()

        txtInstruction.text =
            "Convert Base ${question.fromBase} → Base ${question.toBase}"

        txtQuestion.text =
            question.getQuestionText()

        edtAnswer.setText("")

        startTimer()
    }

    private fun startTimer() {

        timer = object : CountDownTimer(
            questionTime,
            1000
        ) {

            override fun onTick(millisUntilFinished: Long) {

                txtTimer.text =
                    (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {

                gameManager.player.loseLife()

                updateUI()

                if (gameManager.player.lives <= 0) {

                    Toast.makeText(
                        this@MainActivity,
                        "Game Over",
                        Toast.LENGTH_LONG
                    ).show()

                    showGameOver()

                } else {

                    loadQuestion()
                }
            }
        }

        timer?.start()
    }

    private fun updateUI() {

        txtScore.text =
            "Score: ${gameManager.player.score}"

        txtLives.text =
            "Lives: ${gameManager.player.lives}"
    }
    private fun showGameOver() {

        timer?.cancel()

        txtGameOver.visibility = View.VISIBLE

        btnRestart.visibility = View.VISIBLE

        btnSubmit.isEnabled = false

        edtAnswer.isEnabled = false

        txtGameOver.text =
            "GAME OVER\nScore : ${gameManager.player.score}"
    }

}