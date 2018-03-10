package iosoft.adidasgo

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class DataViewModel : ViewModel() {
    private lateinit var onRoute : MutableLiveData<Boolean>
    private lateinit var finishRoute : MutableLiveData<Boolean>

    public fun setOnRoute (b : Boolean){
        onRoute.value = b
    }

    public fun getOnRoute () : Boolean {
        return onRoute.value!!
    }

    public fun setFinishRoute (b : Boolean){
        finishRoute.value = b
    }

    public fun getFinishRoute () : Boolean {
        return finishRoute.value!!
    }
}