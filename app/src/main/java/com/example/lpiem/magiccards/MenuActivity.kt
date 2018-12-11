package com.example.lpiem.magiccards

import Models.User
import Views.UserCardList
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.facebook.AccessToken
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.ResultCallback
import com.google.android.gms.common.api.Status
import com.squareup.picasso.Picasso
import controllers.InterfaceCallBackController
import controllers.MagicCardRetrofitController
import kotlinx.android.synthetic.main.activity_menu.*
import org.json.JSONObject


class MenuActivity : AppCompatActivity(),InterfaceCallBackController {

    private var tvId: TextView? = null
    private var ivUserPicture: ImageView? = null

    private var response: JSONObject? = null
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
        ivUserPicture = findViewById(R.id.ivUserPicture);
        val tvUserEmail:TextView? = findViewById(R.id.tvUserEmail);

        try {
            acct = intent.getParcelableExtra<Parcelable>("ACCOUNT") as GoogleSignInAccount?
            Log.d("GoogleId",acct!!.id.toString())
            connexionToTheAppWithGoogle(acct!!.id.toString())
        }catch (e: Exception){
            connexionToTheAppWithGoogle("113363234856734569174")
        }

    }

    override fun onWorkDone(result: Any) {
        if (result as Boolean){
            Log.d("workDone","WORKDONE")
            Log.d("workDone", user!!.name)

            try {

                val jsondata = intent.getStringExtra("userProfile")
                response = JSONObject(jsondata)
                tvUserEmail!!.setText(response!!.get("email").toString())
                tvUserName!!.setText(response!!.get("name").toString())
                profile_pic_data = JSONObject(response!!.get("picture").toString())
                profile_pic_url = JSONObject(profile_pic_data!!.getString("data"))
                Picasso.get()
                        .load(profile_pic_url!!.getString("url"))
                        .placeholder(R.drawable.image_profil)
                        .into(ivUserPicture)

            } catch (e: Exception) {
                e.printStackTrace()
            }


            try {
                acct = intent.getParcelableExtra<Parcelable>("ACCOUNT") as GoogleSignInAccount?
                acct!!.id.toString()
                tvUserEmail!!.setText(acct!!.displayName.toString())
                tvUserName!!.setText(acct!!.email.toString())

                val a : String = acct!!.photoUrl.toString()

                Picasso.get()
                        .load(acct!!.photoUrl.toString())
                        .into(ivUserPicture)


            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }



    fun logOut(view: View) {

        if (response != null) {
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
        val intent2 = Intent(this, UserCardList::class.java)
        startActivity(intent2)
    }


    private fun connexionToTheAppWithGoogle(googleId: String) {


        val controller = MagicCardRetrofitController(this )
        controller.callUserGoogleId(googleId,user)


    }

}
