package iosoft.adidasgo

import android.util.Log
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class ApiHandler {
    companion object {

        var IP : String = "10.1.57.88"
        var Port : String = "8888"

        fun isRegistered(id : String): Response {
            val (request, response, result) = ("http://" + IP + ":" + Port + "/isRegistered/" + id).httpGet().responseString()
            return response
        }

        fun signIn(name : String, email : String, lastName : String, idGoogle : String, token: String, team : String, urlPhoto : String): Response {
            val (request, response, result) = ("http://" + IP + ":" + Port + "/signIn").httpPost().body("{ \"name\" : \"" + name + "\", \"email\" : \"" + email + "\", \"lastName\" : \"" + lastName + "\", \"idGoogle\" : \"" + idGoogle + "\", \"token\" : \"" + token + "\", \"team\" : \"" + team + "\", \"urlPhoto\" : \"" + urlPhoto + "\"}").responseString()
            return response
        }

    }
}
