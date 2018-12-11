package com.example.lpiem.magiccards

import Views.BottomNavigationActivity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.android.synthetic.main.activity_main.*
import com.facebook.FacebookException
import android.content.Intent
import com.facebook.GraphRequest
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task


class MainActivity : AppCompatActivity() {

    private var mGoogleSignInClient: GoogleSignInClient? = null

    private val RC_SIGN_IN: Int = 9001

    private var callbackManager: CallbackManager? = null

    private var accessToken: AccessToken? = null

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
        button_sign_out.setOnClickListener{ signOut() }
        button_disconnect.setOnClickListener { revokeAccess() }
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
        val intent = Intent(this@MainActivity, BottomNavigationActivity::class.java)
        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            intent.putExtra("google", acct)
            startActivity(intent)
        }

    }

    protected fun getUserDetails(loginResult: AccessToken) {
        val data_request = GraphRequest.newMeRequest(
                loginResult) { json_object, response ->
            val intent = Intent(this@MainActivity, BottomNavigationActivity::class.java)
            intent.putExtra("facebook", json_object.toString())
            startActivity(intent)
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

}
