package iosoft.adidasgo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.content.Intent
import android.util.Log
import android.util.Log.d
import android.widget.Toast
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.getAs
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient

import kotlinx.android.synthetic.main.activity_login.*

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {

    private val RC_SIGN_IN = 9001;
    private var mGoogleApiClient: GoogleApiClient? = null;

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        d("bett", "onConnectionFailed");
    }

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Set up the login form.

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        mGoogleApiClient = GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()

        email_sign_in_button?.setOnClickListener(View.OnClickListener {
            var signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
            startActivityForResult(signInIntent, RC_SIGN_IN)
        })

    }

    companion object {

        /**
         * Id to identity READ_CONTACTS permission request.
         */
        private val REQUEST_READ_CONTACTS = 0

        /**
         * A dummy authentication store containing known user names and passwords.
         * TODO: remove after connecting to a real authentication system.
         */
        private val DUMMY_CREDENTIALS = arrayOf("foo@example.com:hello", "bar@example.com:world")
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            var result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            signInAccount = result
            handleSignInResult(result)
        }
    }
//    db.name = req.body.name;
//    db.email = req.body.email;
//    db.lastName  = req.body.lastName;
//    db.idGoogle = req.body.idGoogle;
//    db.token = req.body.token;
//    db.team = req.body.team;
//    db.urlPhoto = req.body.urlPhoto;

    private lateinit var signInAccount: GoogleSignInResult

    private fun handleSignInResult(result: GoogleSignInResult?) {
        if (result?.isSuccess!!) {
            signInAccount = result
            var id = result.signInAccount!!.id
            //var resp : Response = ApiHandler.isRegistered(id.toString())
            ("http://" + ApiHandler.IP + ":" + ApiHandler.Port + "/isRegistered/" + id).httpGet()
                    .responseString { request, response, result ->
                        callbackResponse(request, response, result)
            }



//            var response : Response = ApiHandler.signIn(result.signInAccount!!.displayName!!, result.signInAccount!!.email!!, result.signInAccount!!.familyName!!, result.signInAccount!!.id!!, "", "potato", result.signInAccount!!.photoUrl.toString()!!)
//            d("POLLAMEN", response.toString())

        }

       // TODO: Enviar las cosas que necesitemos
    }

    private fun callbackResponse(request: Request, response: Response, result: Result<String, FuelError>) {
        //do something with response
        var zero : Long = 0
        d("RESULTio", result.toString())
        when (result) {
            is Result.Failure -> {
                d("FAILio", response.toString())
                updateUI(false)
             }
            is Result.Success -> {
                d("RESULLLLLLLT", response.toString())
                updateUI(true)
            }
        }
    }

    private fun updateUI(isLogin: Boolean) {
        if(isLogin) {
            // TODO: Peticion para registrar o si ya esta con la sesion iniciada habra que cargar su perfil
            val mapsactivity = Intent(this, MapsActivity::class.java)
            startActivity(mapsactivity)
        } else {
            val selectTeamActivity = Intent(this, SelectTeamActivity::class.java)
            selectTeamActivity.putExtra("familyName", signInAccount.signInAccount?.familyName)
            selectTeamActivity.putExtra("id", signInAccount.signInAccount?.id)
            selectTeamActivity.putExtra("displayName", signInAccount.signInAccount?.displayName)
            selectTeamActivity.putExtra("email", signInAccount.signInAccount?.email)
            selectTeamActivity.putExtra("urlPhoto", signInAccount.signInAccount?.photoUrl.toString())
            startActivity(selectTeamActivity)
        }
    }


}
