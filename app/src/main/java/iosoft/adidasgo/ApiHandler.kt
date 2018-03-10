package iosoft.adidasgo

import android.util.Log
import android.widget.Toast
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

/**
 * Created by belbus on 10/03/18.
 */
class APiHandler {
    companion object {

        private var IP : String = "10.3.11.96"
        private var Port : String = "8888"

        fun isRegistered(id : String) {
           // val result = URL("http://" + IP + ":" + Port + "/isRegistered/" + id).readText()
            doAsync{
                val result = URL("http://" + IP + ":" + Port + "/isRegistered/" + id).readText()

                Log.d("Result", result.toString())
                println("POLLAMEN " + result.toString())
            }
        }
    }
}