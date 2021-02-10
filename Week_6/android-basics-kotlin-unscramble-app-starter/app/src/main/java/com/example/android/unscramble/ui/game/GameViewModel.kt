package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private var wordsList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String

    // Declare private mutable variable that can only be modified
    // within the class it is declared.
    private var _count = 0

    private var currentWordCount = 0
    private lateinit var _currentScrambledWord: String

    // Declare another public immutable field and override its getter method.
    // Return the private property's value in the getter method.
    // When count is accessed, the get() function is called and
    // the value of _count is returned.
    private var _score = 0
    val score: Int
        get() = _score

    val count: Int
        get() = _count

    val currentScrambledWord: String
        get() = _currentScrambledWord

    // initializer block
    init {
        Log.d("GameFragment", "GameViewModel created!")

        //To prevent first word always the same
        getNextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment", "GameViewModel destroyed!")
    }

    private fun getNextWord(){
        currentWord = allWordsList.random()
        val tempWord = currentWord.toCharArray()
        tempWord.shuffle()

        while (tempWord.toString().equals(currentWord, false)) {
            tempWord.shuffle()
        }
        if (wordsList.contains(currentWord)) {
            getNextWord()
        } else {
            _currentScrambledWord = String(tempWord)
            ++currentWordCount
            wordsList.add(currentWord)
        }
    }

    /*
    * Returns true if the current word count is less than MAX_NO_OF_WORDS.
    * Updates the next word.
    */
    fun nextWord(): Boolean {
        return if (currentWordCount < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }

    private fun increaseScore(){
        _score += SCORE_INCREASE
    }

    fun isUserWordCorrect(playerWord: String): Boolean{
        if(playerWord.equals(currentWord, true)){
            increaseScore()
            return true
        }

        return false
    }
}