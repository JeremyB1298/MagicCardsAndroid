package Views

import Fragments.CardRecyclerViewFragment
import Fragments.Fragment_home
import Fragments.Fragment3
import Models.User
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
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


class BottomNavigationActivity : AppCompatActivity() {

    private var content: FrameLayout? = null
    private var user: User? = null

    private val mOnNavigationItemSelectedListener = object : BottomNavigationView.OnNavigationItemSelectedListener {

        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.navigation_1 -> {

                    val fragment = Fragment_home.newInstance(user!!)
                    addFragment(fragment)
                    return true
                }
                R.id.navigation_2 -> {
                    val fragment = CardRecyclerViewFragment()
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
    private fun addFragment(fragment: androidx.fragment.app.Fragment) {
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
                .replace(R.id.content, fragment, fragment.javaClass.getSimpleName())
                .addToBackStack(fragment.javaClass.getSimpleName())
                .commit()
    }
    private fun addHomeFragment(fragment: Fragment) {
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

        content = findViewById(R.id.content) as FrameLayout
        val navigation = findViewById(R.id.navigation) as BottomNavigationView
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        user = intent.getSerializableExtra("user") as User
        val fragment = Fragment_home.newInstance(user!!)

        addHomeFragment(fragment)
    }

}