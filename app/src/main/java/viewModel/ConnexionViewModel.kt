
package viewModel

import Models.Card
import Models.CardDB
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

object ConnexionViewModel: AppCompatActivity() {
    var mGoogleSignInClient: GoogleSignInClient? = null

    val RC_SIGN_IN: Int = 9001

    var accessToken: AccessToken? = null

    var userManager = Managers.UserManager.getInstance();

    var acctGoogle: GoogleSignInAccount? = null

    var acctFacebook: JSONObject? = null

    var callbackManager: CallbackManager? = null

    var isLoggedIn: Boolean? = null

    var activity : Activity?=null

    var inter : InterfaceCallBackController? = null

    fun initialize(activity: Activity, inter: InterfaceCallBackController) {
        var gso: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(activity,gso)


        callbackManager = CallbackManager.Factory.create()

        accessToken= AccessToken.getCurrentAccessToken()
        isLoggedIn = accessToken != null && !accessToken!!.isExpired()
        this.activity = activity
        this.inter = inter
    }

    fun getUserDetails(loginResult: AccessToken) {
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


    private fun connexionToTheAppWithFacebook(fbId: String) {
        val controller = MagicCardRetrofitController(this!!.inter!! )
        controller.callUserFbId(fbId,userManager.getUser()!!)
    }
}