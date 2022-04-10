package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {


    // The current word
     private var _word = MutableLiveData<String>()
     val word : LiveData<String>
         get() =_word


    // The current score
     private var _score = MutableLiveData<Int>()
     val score : LiveData<Int>
        get() =_score

    // when game finish
    private var _isFinished = MutableLiveData<Boolean>()
    val isFinished : LiveData<Boolean>
        get() = _isFinished

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }

     fun onSkip() {
         _score.value = _score.value?.minus(1)
        nextWord()
    }

     fun onCorrect() {
        _score.value = _score.value?.plus(1)
        nextWord()
    }

    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        if (!wordList.isEmpty()) {
            //Select and remove a word from the list
            _word.value = wordList.removeAt(0)
        } else{
            _isFinished.value = true
        }
    }

    fun onGameFinishComplete() {
        _isFinished.value = false
    }


    init {
        _word.value=""
        _score.value=0
       resetList()
        nextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
    }

}