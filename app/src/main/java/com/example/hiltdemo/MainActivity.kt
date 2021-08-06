package com.example.hiltdemo

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var someClass: SomeClass
    val TAG = "MyTestClass";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Example 1
        //Log.d("MyTestClass","Hello ${someClass.doThing()}")
        // Log.d("MyTestClass","Hello 2${someClass.doOtherThing()}")

        //Interface example

        Log.d("MyTestClass", "onCreate: " + someClass.doThing())
    }
}

//Example 1
/*
class  SomeClass
    @Inject
    constructor(val someOtherClass: SomeOtherClass){
        fun  doThing(): String
        {
            return " Hilt test !"
        }

        fun  doOtherThing():String
        {
            return  someOtherClass.doThing()
        }

    }

class SomeOtherClass
@Inject
    constructor(){
     fun doThing(): String {
        return "inteface worked"
    }


}*/


class SomeClass
@Inject
constructor(private val someInterfaceImpl: SomeInterface) {
    fun doThing(): String {
        return " Hilt test ! ${someInterfaceImpl.printString()}"
    }
}

// Option or way 1

/*@InstallIn(SingletonComponent::class)
//@InstallIn(ApplicationComponent::class)
@Module
abstract  class  MyModule {
    @Singleton
    @Binds
    abstract fun  bindSomeDependancy (someImpl : SomeInterfaceImpl):SomeInterface

}*/

//Second way to call interface

@InstallIn(SingletonComponent::class)
@Module
class MyModule {
    @Singleton
    @Provides
    fun provideSomeInterface() :SomeInterface{
        return  SomeInterfaceImpl()
    }
}


