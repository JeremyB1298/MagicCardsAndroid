package Fragments

import Models.User
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
        val user = arguments?.getSerializable(INTENT_HOME_ID_USER) as User
        Log.d("userFragment", user.googleId.toString())
        val tvUserName: TextView = rootView.findViewById(R.id.tvName);
        val tvUserLvl: TextView = rootView.findViewById(R.id.tvLvl);
        val tvUserExp: TextView = rootView.findViewById(R.id.tvExp);
        tvUserName.text = user.name
        tvUserLvl.text = user.lvl.toString()
        tvUserExp.text = user.exp.toString()
        return rootView
    }

    companion object {
        fun newInstance(user: User): Fragment_home {
            val fragment = Fragment_home()
            val args = Bundle()
            args.putSerializable(INTENT_HOME_ID_USER,user)
            fragment.arguments = args

            return fragment
        }
    }
}