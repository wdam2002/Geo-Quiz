package com.bignerdranch.android.geoquiz

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"
const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
const val IS_CHEATER_KEY = "IS_CHEATER_KEY"

class QuizViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    val questionBank = listOf(
        Question(R.string.question_europe, true,),
        Question(R.string.question_turkey, false),
        Question(R.string.question_paris, true),
        Question(R.string.question_china, true),
        Question(R.string.question_america, false),
        Question(R.string.question_iceland, true)
    )

    var isCheater: Boolean
        get() = savedStateHandle.get(IS_CHEATER_KEY) ?: false
        set(value) = savedStateHandle.set(IS_CHEATER_KEY, value)

    private var currentIndex: Int
        get() = savedStateHandle.get(CURRENT_INDEX_KEY) ?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)

    var numQuestions: Int = 0
    var numCorrect: Int = 0
    val currentQuestion: Question
        get() = questionBank[currentIndex]
    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer
    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    fun moveToPrev() {
        if (currentIndex > 0) {
            currentIndex = (currentIndex - 1) % questionBank.size
        }
    }

    fun moveToNext() {
        if (currentIndex < questionBank.size - 1) {
            currentIndex = (currentIndex + 1) % questionBank.size
        }
    }
}