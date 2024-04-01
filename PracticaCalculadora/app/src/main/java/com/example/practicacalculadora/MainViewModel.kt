package com.example.practicacalculadora

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _result: MutableLiveData<String> by lazy {
        MutableLiveData<String>("")
    }
    val result: LiveData<String> get() = _result
    private val _showDivisionError: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val showDivisionError: LiveData<Boolean> get() = _showDivisionError
    private val _enableMemoryButtons: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val enableMemoryButtons: LiveData<Boolean> get() = _enableMemoryButtons
    private var currentOperation: OperationType = OperationType.NONE
    private var prevNumber: Int = 0
    private var memory: Int = 0

    fun clearMemory() {
        memory = 0
        _enableMemoryButtons.value = false
    }

    fun readFromMemory() {
        _result.value = memory.toString()
    }

    fun subtractFromMemory() {
        _enableMemoryButtons.value = true
        var numToSubstract = 0
        if (_result.value!!.isNotEmpty()) {
            numToSubstract = _result.value!!.toInt()
        }
        memory -= numToSubstract
    }


    fun addToMemory() {
        _enableMemoryButtons.value = true
        var numToAdd = 0
        if (_result.value!!.isNotEmpty()) {
            numToAdd = _result.value!!.toInt()
        }
        memory += numToAdd
    }

    fun doOperation() {
        var currentNumber = 0
        if (_result.value!!.isNotEmpty()) {
            currentNumber = _result.value!!.toInt()
        }
        val operationResult = when (currentOperation) {
            OperationType.ADDITION -> prevNumber + currentNumber
            OperationType.SUBTRACTION -> prevNumber - currentNumber
            OperationType.MULTIPLICATION -> prevNumber * currentNumber
            OperationType.DIVISION -> {
                if (currentNumber == 0) {
                    _showDivisionError.value = true
                    return
                }
                prevNumber / currentNumber
            }

            OperationType.NONE -> currentNumber
        }
        _result.value = operationResult.toString()
    }

    fun startOperation(operation: OperationType) {
        prevNumber = 0
        if (_result.value!!.isNotEmpty()) {
            prevNumber = _result.value!!.toInt()
        }
        currentOperation = operation
        _result.value = ""
    }

    fun clearEverything() {
        _result.value = ""
    }

    fun clearOne() {
        if (_result.value!!.isEmpty()) {
            return
        }
        _result.value = _result.value!!.substring(0, _result.value!!.length - 1)
    }

    fun appendNumber(num: Int) {
        _result.value += num
    }
}