package views

import Fragments.CardRecyclerViewFragment
import Fragments.FragmentShop
import Fragments.Fragment_home
import Managers.UserManager
import Models.User
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.lpiem.magiccards.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class BottomNavigationActivity : AppCompatActivity() {

    private var content: FrameLayout? = null
    private var user: User? = null

    private val mOnNavigationItemSelectedListener = object : BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                android.R.id.home ->{
                    displayText()
                    return true
                }
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
                    var fragment = FragmentShop()
                    addFragment(fragment)
                    return true
                }
            }
            return false
        }

    }

    @Override
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        val toolbar = supportActionBar

        toolbar!!.setDisplayHomeAsUpEnabled(false)
        return true
    }

    fun displayText() {
        Toast.makeText(this,"Home action", Toast.LENGTH_LONG).show()
    }

    /**
     * add/replace fragment in container [framelayout]
     */
    private fun addFragment(fragment: androidx.fragment.app.Fragment) {
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
                .replace(R.id.content, fragment, fragment.javaClass.getSimpleName())
                .commit()
    }
    private fun addHomeFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
                .replace(R.id.content, fragment, fragment.javaClass.getSimpleName())
                .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)

        content = findViewById(R.id.content) as FrameLayout
        val navigation = findViewById(R.id.navigation) as BottomNavigationView
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        user = UserManager.user
        val fragment = Fragment_home.newInstance(user!!)

        addHomeFragment(fragment)
    }

}