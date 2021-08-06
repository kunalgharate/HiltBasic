package com.example.hiltdemo

import javax.inject.Inject

class SomeInterfaceImpl
@Inject
constructor() : SomeInterface {

    override fun printString(): String {
        return "Hello from interface impl"
    }
}

interface SomeInterface {
    fun printString(): String
}

