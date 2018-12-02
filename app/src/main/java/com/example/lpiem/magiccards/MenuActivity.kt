package com.example.lpiem.magiccards

import Models.User
import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.GoogleApiClient
import android.support.v4.widget.DrawerLayout
import org.json.JSONObject
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.example.lpiem.magiccards.R.id.ivUserPicture
import com.squareup.picasso.Picasso
import com.example.lpiem.magiccards.R.id.tvUserName
import com.example.lpiem.magiccards.R.id.tvUserEmail
import android.widget.TextView
import com.example.lpiem.magiccards.R.id.bLogOut
import android.content.Intent
import android.os.Parcelable
import android.support.design.widget.NavigationView
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import Views.UserCardList
import android.widget.Toast
import com.facebook.login.LoginManager
import com.facebook.AccessToken
import android.support.v4.view.GravityCompat
import android.util.Log
import com.google.android.gms.common.api.ResultCallback
import com.google.android.gms.common.api.Status
import controllers.InterfaceCallBackController
import controllers.MagicCardRetrofitController
import kotlinx.android.synthetic.main.activity_menu.*


class MenuActivity : AppCompatActivity(),InterfaceCallBackController {

    private var tvId: TextView? = null
    private var ivUserPicture: ImageView? = null

    private var fbAccount: JSONObject? = null
    private var profile_pic_data: JSONObject? = null
    private var profile_pic_url: JSONObject? = null
    private var user: User? = User(-1)

    private var mGoogleApiClient: GoogleApiClient? = null

    private var acct: GoogleSignInAccount? = null


    override fun onStart() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        mGoogleApiClient = GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()
        mGoogleApiClient!!.connect()
        super.onStart()
    }

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val tvUserName:TextView? = findViewById(R.id.tvUserName);
        val ivUserPicture:ImageView? = findViewById(R.id.ivUserPicture);
        val tvUserEmail:TextView? = findViewById(R.id.tvUserEmail);

        //setSupportActionBar(toolbar)
        val actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        actionbar.setHomeAsUpIndicator(R.drawable.ic_launcher_foreground)

        val mDrawerLayout: DrawerLayout? = findViewById(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(
                object : NavigationView.OnNavigationItemSelectedListener {
                    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true)
                        // close drawer when item is tapped
                        mDrawerLayout!!.closeDrawers()

                        when (menuItem.getItemId()) {
                            R.id.logOutNavDraw -> logOut(findViewById(android.R.id.content))

                            R.id.listCardPage -> goToCardList(findViewById(android.R.id.content))
                        }//Action;
                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true
                    }
                })
        try {
            acct = intent.getParcelableExtra<Parcelable>("ACCOUNT") as GoogleSignInAccount?
            Log.d("GOOGLEID", acct!!.id.toString())
            connexionToTheAppWithGoogle(acct!!.id.toString())
        }catch (e: Exception){

        }
        try {
            val fbAccountJson = intent.getStringExtra("userProfile")
            fbAccount = JSONObject(fbAccountJson)
            Log.d("FBID", fbAccount!!.get("id").toString())
            connexionToTheAppWithFacebook(fbAccount!!.get("id").toString())
        }catch (e: Exception){

        }

    }

    override fun onWorkDone(result: Any) {
        if (result is Map<*, *>) {
            if (result["google"] === true) {
                try {
                    acct = intent.getParcelableExtra<Parcelable>("ACCOUNT") as GoogleSignInAccount?
                    acct!!.id.toString()
                    tvUserEmail!!.setText(acct!!.displayName.toString())
                    tvUserName!!.setText(acct!!.email.toString())
                    profile_pic_url = JSONObject(acct!!.photoUrl.toString())
                    Picasso.get()
                            .load(profile_pic_url!!.getString("url"))
                            .placeholder(R.drawable.image_profil)
                            .into(ivUserPicture)


                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else if (result["facebook"] === true) {
                try {

                    val jsondata = intent.getStringExtra("userProfile")
                    fbAccount = JSONObject(jsondata)
                    tvUserEmail!!.setText(fbAccount!!.get("email").toString())
                    tvUserName!!.setText(fbAccount!!.get("name").toString())
                    profile_pic_data = JSONObject(fbAccount!!.get("picture").toString())
                    profile_pic_url = JSONObject(profile_pic_data!!.getString("data"))
                    Picasso.get()
                            .load(profile_pic_url!!.getString("url"))
                            .placeholder(R.drawable.image_profil)
                            .into(ivUserPicture)

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val mDrawerLayout: DrawerLayout? = findViewById(R.id.drawer_layout)
                mDrawerLayout!!.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun logOut(view: View) {

        if (fbAccount != null) {
            AccessToken.setCurrentAccessToken(null)
            LoginManager.getInstance().logOut()
            val intent = Intent(this@MenuActivity, MainActivity::class.java)
            startActivity(intent)
        }

        if (acct != null) {
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    object : ResultCallback<Status> {
                        override fun onResult(status: Status) {
                            // ...
                            Toast.makeText(applicationContext, "Logged Out", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@MenuActivity, MainActivity::class.java)
                            startActivity(intent)
                        }
                    })
        }
    }

    fun goToCardList(view: View) {
        val intent = Intent(this@MenuActivity, UserCardList::class.java)
        startActivity(intent)
    }


    private fun connexionToTheAppWithGoogle(googleId: String) {
        val controller = MagicCardRetrofitController(this )
        controller.callUserGoogleId(googleId, user)
    }

    private fun connexionToTheAppWithFacebook(fbId: String) {
        val controller = MagicCardRetrofitController(this )
        controller.callUserFbId(fbId, user)
    }

}
