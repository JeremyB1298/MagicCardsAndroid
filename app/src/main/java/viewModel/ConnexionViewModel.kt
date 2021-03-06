
package viewModel

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.GraphRequest
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import controllers.InterfaceCallBackController
import controllers.MagicCardRetrofitController
import org.json.JSONObject

@SuppressLint("StaticFieldLeak")
object ConnexionViewModel {



    //class ConnexionViewModel(val app : Application) : AndroidViewModel(app) {

    var insription: MutableLiveData<String>? = null

    var mGoogleSignInClient: GoogleSignInClient? = null

    val RC_SIGN_IN: Int = 9001

    var accessToken: AccessToken? = null

    var acctGoogle: GoogleSignInAccount? = null

    var acctFacebook: JSONObject? = null

    var callbackManager: CallbackManager? = null

    var isLoggedIn: Boolean? = null

    var activity : Activity?=null

    var inter : InterfaceCallBackController? = null

    fun initialize(activity: Activity, inter : InterfaceCallBackController) {
        var gso: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(activity,gso)

    //app.applicationContext

        callbackManager = CallbackManager.Factory.create()

        accessToken= AccessToken.getCurrentAccessToken()
        isLoggedIn = accessToken != null && !accessToken!!.isExpired()
        this.activity = activity
        this.inter = inter
        insription = MutableLiveData()
    }

    fun getUserDetails(loginResult: AccessToken) {
        val data_request = GraphRequest.newMeRequest(
                loginResult) { json_object, response ->
            acctFacebook = json_object
            Managers.UserManager.user!!.fbId = acctFacebook!!.get("id").toString()
            Managers.UserManager.user!!.name = acctFacebook!!.get("name").toString()
            connexionToTheAppWithFacebook(acctFacebook!!.get("id").toString())
        }
        val permission_param = Bundle()
        permission_param.putString("fields", "id,name,email,picture.width(120).height(120)")
        data_request.parameters = permission_param
        data_request.executeAsync()

    }

    public fun signIn(activity : AppCompatActivity) {
        val signInIntent = mGoogleSignInClient!!.getSignInIntent()
        activity.startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>, context: Context) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            //Toast.makeText(this, "Connecte", Toast.LENGTH_SHORT).show()
            this.goToMenuActivity(context)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Connect", "signInResult:failed code=" + e.statusCode)
            //Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }

    }

    public fun signInResult(requestCode: Int, resultCode: Int, data: Intent?, context: Context){
        ConnexionViewModel.callbackManager!!.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ConnexionViewModel.RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.


            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task, context)
        }
    }

    private fun goToMenuActivity(context: Context) {

        acctGoogle = GoogleSignIn.getLastSignedInAccount(context)
        if (acctGoogle != null) {
            connexionToTheAppWithGoogle(acctGoogle!!.id.toString())
        }

    }

    fun connexionToTheAppWithFacebook(fbId: String) {
        val controller = MagicCardRetrofitController(this!!.inter!! )
        controller.callUserFbId(fbId, Managers.UserManager.user)
    }

    private fun connexionToTheAppWithGoogle(googleId: String) {
        val controller = MagicCardRetrofitController(this.inter!!)
        controller.callUserGoogleId(googleId, Managers.UserManager.user)
    }


}