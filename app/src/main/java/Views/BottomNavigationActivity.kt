package Views

import Fragments.Fragment_home
import Fragments.Fragment2
import Fragments.Fragment3
import Models.User
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcelable
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.widget.FrameLayout
import com.example.lpiem.magiccards.R
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.squareup.picasso.Picasso
import controllers.AccountConnexion
import controllers.InterfaceCallBackController
import controllers.MagicCardRetrofitController
import kotlinx.android.synthetic.main.activity_menu.*
import org.json.JSONObject


class BottomNavigationActivity : AppCompatActivity(), InterfaceCallBackController {

    private var content: FrameLayout? = null
    private var user = User(-1)
    private var acctGoogle: GoogleSignInAccount? = null
    private var acctFacebook: JSONObject? = null

    private val mOnNavigationItemSelectedListener = object : BottomNavigationView.OnNavigationItemSelectedListener {

        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.navigation_1 -> {

                    val fragment = Fragment_home()
                    addFragment(fragment)

                    return true
                }
                R.id.navigation_2 -> {
                    val fragment = Fragment2()
                    addFragment(fragment)
                    return true
                }
                R.id.navigation_3 -> {
                    var fragment = Fragment3()
                    addFragment(fragment)
                    return true
                }
            }
            return false
        }

    }

    /**
     * add/replace fragment in container [framelayout]
     */
    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
                .replace(R.id.content, fragment, fragment.javaClass.getSimpleName())
                .addToBackStack(fragment.javaClass.getSimpleName())
                .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)

        if (intent.getParcelableExtra<Parcelable>("google") != null) {
            acctGoogle = intent.getParcelableExtra<Parcelable>("google") as GoogleSignInAccount?
            Log.d("GOOGLEID", acctGoogle!!.id.toString())
            connexionToTheAppWithGoogle(acctGoogle!!.id.toString())
        } else {
            val fbAccountJson = intent.getStringExtra("facebook")
            acctFacebook = JSONObject(fbAccountJson)
            Log.d("FBID", acctFacebook!!.get("id").toString())
            connexionToTheAppWithFacebook(acctFacebook!!.get("id").toString())
        }

        content = findViewById(R.id.content) as FrameLayout
        val navigation = findViewById(R.id.navigation) as BottomNavigationView
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val fragment = Fragment_home()
        val bundle = Bundle()
        bundle.putString("edttext", "From Activity")

        addFragment(fragment)
    }

    private fun connexionToTheAppWithGoogle(googleId: String) {
        val controller = MagicCardRetrofitController(this )
        controller.callUserGoogleId(googleId, user)
    }

    private fun connexionToTheAppWithFacebook(fbId: String) {
        val controller = MagicCardRetrofitController(this )
        controller.callUserFbId(fbId, user)
    }

    private fun inscriptionGoogleAccount(googleId: String,name: String) {
        val controller = MagicCardRetrofitController(this )
        controller.createUser(User(0,"0",googleId,name,true));
    }

    @SuppressLint("LongLogTag")
    override fun onWorkDone(result: Any) {
        if (result is Map<*, *>) {
            if (result["google"] === true) {
                try {
                    user.googleId = acctGoogle!!.id.toString()

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else if (result["facebook"] === true) {
                try {


                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else if (result["google"] === false){
                acctGoogle = intent.getParcelableExtra<Parcelable>("google") as GoogleSignInAccount?
                inscriptionGoogleAccount(acctGoogle!!.id.toString(),acctGoogle!!.displayName.toString())
            }
        }
    }
}