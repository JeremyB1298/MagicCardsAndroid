package com.example.lpiem.magiccards

import Models.User
import Views.BottomNavigationActivity
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.*
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import controllers.InterfaceCallBackController
import controllers.MagicCardRetrofitController
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject


class MainActivity : AppCompatActivity(), InterfaceCallBackController {

    private var mGoogleSignInClient: GoogleSignInClient? = null

    private val RC_SIGN_IN: Int = 9001

    private var callbackManager: CallbackManager? = null

    private var accessToken: AccessToken? = null

    private var user = User(-1)

    private var acctGoogle: GoogleSignInAccount? = null
    private var acctFacebook: JSONObject? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var gso: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this,gso)


        Toast.makeText(this, "account",Toast.LENGTH_SHORT).show()

        callbackManager = CallbackManager.Factory.create()

        accessToken= AccessToken.getCurrentAccessToken()
        var isLoggedIn: Boolean = accessToken != null && !accessToken!!.isExpired()

        if(isLoggedIn) {
            getUserDetails(AccessToken.getCurrentAccessToken())
        }

        login_button.setReadPermissions("email")

        //Bouton login de facebook
        login_button.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {

                getUserDetails(loginResult.accessToken)

                Log.e("succesLogButton", "yes")
            }

            override fun onCancel() {
                Log.e("succesLogButtonC", "no")
            }

            override fun onError(exception: FacebookException) {
                Log.e("succesLogButtonE", "no")
            }
        })

        //butons pour le compte google
        sign_in_button.setOnClickListener { signIn() }
    }

    override fun onResume() {
        super.onResume()

        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            val personName = acct.displayName
            val personGivenName = acct.givenName
            val personFamilyName = acct.familyName
            val personEmail = acct.email

 //           Log.d("PersonalInfo", "person email: " + personEmail!!)
            val personId = acct.id
 //           Log.d("PersonalInfo", "person Name: " + personName!!)
            val personPhoto = acct.photoUrl
        }
    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient!!.getSignInIntent()
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun signOut() {
        Log.d("connect", "signOut: ")
        mGoogleSignInClient!!.signOut()
                .addOnCompleteListener(this) {
                    // ...
                }
    }

    private fun revokeAccess() {
        Log.d("connect", "revokeAccess: ")
        mGoogleSignInClient!!.revokeAccess()
                .addOnCompleteListener(this) {
                    // ...
                }
    }


    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            Toast.makeText(this, "Connecte", Toast.LENGTH_SHORT).show()
            this.goToMenuActivity()
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Connect", "signInResult:failed code=" + e.statusCode)
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }

    }

    private fun goToMenuActivity() {

        acctGoogle = GoogleSignIn.getLastSignedInAccount(this)
        if (acctGoogle != null) {
            connexionToTheAppWithGoogle(acctGoogle!!.id.toString())
        }

    }

    protected fun getUserDetails(loginResult: AccessToken) {
        val data_request = GraphRequest.newMeRequest(
                loginResult) { json_object, response ->
            acctFacebook = json_object
            connexionToTheAppWithFacebook(acctFacebook!!.get("id").toString())
        }
        val permission_param = Bundle()
        permission_param.putString("fields", "id,name,email,picture.width(120).height(120)")
        data_request.parameters = permission_param
        data_request.executeAsync()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager!!.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.


            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    @SuppressLint("LongLogTag")
    override fun onWorkDone(result: Any) {
        if (result is Map<*, *>) {
            if (result["google"] === true) {
                try {

                    val intent = Intent(this@MainActivity, splashScreenActivity::class.java)
                    intent.putExtra("user", user)
                    startActivity(intent)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else if (result["facebook"] === true) {
                try {
                    val intent = Intent(this@MainActivity, splashScreenActivity::class.java)
                    intent.putExtra("user", user)
                    startActivity(intent)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else if (result["google"] === false){
                inscriptionGoogleAccount(user)
            }
        }
    }


    private fun connexionToTheAppWithGoogle(googleId: String) {
        val controller = MagicCardRetrofitController(this )
        controller.callUserGoogleId(googleId, user)
    }

    private fun connexionToTheAppWithFacebook(fbId: String) {
        val controller = MagicCardRetrofitController(this )
        controller.callUserFbId(fbId, user)
    }

    private fun inscriptionGoogleAccount(user: User) {
        val controller = MagicCardRetrofitController(this )
        controller.createUser(User(0,"0",acctGoogle!!.id.toString(),acctGoogle!!.displayName.toString(),true,0,0));
        connexionToTheAppWithGoogle(user.googleId.toString())
    }

}
