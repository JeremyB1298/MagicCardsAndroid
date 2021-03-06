package com.example.lpiem.magiccards

import Managers.UserManager
import Models.User
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.facebook.AccessToken
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import controllers.InterfaceCallBackController
import controllers.MagicCardRetrofitController
import kotlinx.android.synthetic.main.activity_main.*
import viewModel.ConnexionViewModel


class MainActivity : AppCompatActivity() , InterfaceCallBackController {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ConnexionViewModel.initialize(this,this)


        if(ConnexionViewModel.isLoggedIn!!) {
            ConnexionViewModel.getUserDetails(AccessToken.getCurrentAccessToken())
        }

        login_button.setReadPermissions("email")

        //Bouton login de facebook
        login_button.registerCallback(ConnexionViewModel.callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {

                ConnexionViewModel.getUserDetails(loginResult.accessToken)

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
        sign_in_button.setOnClickListener { ConnexionViewModel.signIn(this) }
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
        val signInIntent = ConnexionViewModel.mGoogleSignInClient!!.getSignInIntent()
        startActivityForResult(signInIntent, ConnexionViewModel.RC_SIGN_IN)
    }

    private fun signOut() {
        Log.d("connect", "signOut: ")
        ConnexionViewModel.mGoogleSignInClient!!.signOut()
                .addOnCompleteListener(this) {
                    // ...
                }
    }

    private fun revokeAccess() {
        Log.d("connect", "revokeAccess: ")
        ConnexionViewModel.mGoogleSignInClient!!.revokeAccess()
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

        ConnexionViewModel.acctGoogle = GoogleSignIn.getLastSignedInAccount(this)
        if (ConnexionViewModel.acctGoogle != null) {
            connexionToTheAppWithGoogle(ConnexionViewModel.acctGoogle!!.id.toString())
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        ConnexionViewModel.signInResult(requestCode, resultCode, data, this)
    }


    override fun onWorkDone(result: Any) {
        if (result is Map<*, *>) {
            if (result["google"] === true) {
                try {

                    val intent = Intent(this@MainActivity, SplashScreenActivity::class.java)
                    startActivity(intent)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else if (result["facebook"] === true) {
                try {
                    val intent = Intent(this@MainActivity, SplashScreenActivity::class.java)
                    startActivity(intent)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else if (result["google"] === false){
                inscriptionGoogleAccount(UserManager.user!!)
                ConnexionViewModel.insription!!.observe(this, Observer {
                    Log.d("NewUserId", UserManager.user!!.googleId!!)
                    connexionToTheAppWithGoogle(UserManager.user!!.googleId!!)
                })

            } else if (result["facebook"] === false) {
                inscriptionFacebookAccount(UserManager.user!!)
                ConnexionViewModel.insription!!.observe(this, Observer {
                    ConnexionViewModel.connexionToTheAppWithFacebook(UserManager.user!!.fbId!!)
                })
            }

        }
    }


    private fun connexionToTheAppWithGoogle(googleId: String) {
        val controller = MagicCardRetrofitController(this )
        controller.callUserGoogleId(googleId, UserManager.user!!)
    }

    private fun inscriptionGoogleAccount(user: User) {
        val controller = MagicCardRetrofitController(this )
        controller.createGoogleUser(User(null,ConnexionViewModel.acctGoogle!!.id.toString(),null,ConnexionViewModel.acctGoogle!!.displayName.toString()));
    }

    private fun inscriptionFacebookAccount(user: User) {
        val controller = MagicCardRetrofitController(this )
        controller.createFacebookUser(User(null, null,ConnexionViewModel.acctFacebook!!.get("id").toString(), ConnexionViewModel.acctFacebook!!.get("name").toString()));
    }

}
