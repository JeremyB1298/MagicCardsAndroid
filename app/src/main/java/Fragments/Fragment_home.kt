package Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lpiem.magiccards.R


/**
 * Created by chirag on 31/7/17.
 */
private const val INTENT_HOME_ID_USER = "User"
class Fragment_home : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater!!.inflate(R.layout.fragment_home, container, false)
        return rootView
    }

    companion object {

        fun newInstance(marvelId: String): Fragment_home {
            val fragment = Fragment_home()
            val args = Bundle()
            args.putString(INTENT_HOME_ID_USER,marvelId)
            fragment.arguments = args

            return fragment
        }
    }
}