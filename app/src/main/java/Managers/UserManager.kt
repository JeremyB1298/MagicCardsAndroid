package Managers

import Models.User
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import org.json.JSONObject

class UserManager private constructor() {

    private var user : User? = null;

    private val mCache: MutableMap<String, String> = mutableMapOf()

    private var mGoogleSignInClient: GoogleSignInClient? = null

    private val RC_SIGN_IN: Int = 9001

    private var callbackManager: CallbackManager? = null

    private var accessToken: AccessToken? = null


    private var acctGoogle: GoogleSignInAccount? = null
    private var acctFacebook: JSONObject? = null

    init {
       ++myInstancesCount
        user = User(-1)
    }


    companion object {
        //Debuggable field to check instance count
        var myInstancesCount = 0;
        private val mInstance: UserManager = UserManager()

        @Synchronized
        fun getInstance(): UserManager {
            return mInstance
        }
    }

    public fun initUserManager(user : User){
        this.user = user;
    }

    public fun getUser(): User? {
        return this.user
    }




}