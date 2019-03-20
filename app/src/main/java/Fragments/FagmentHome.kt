package Fragments

import Models.User
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.lpiem.magiccards.R


/**
 * Created by chirag on 31/7/17.
 */
private const val INTENT_HOME_ID_USER = "User"
class FragmentHome : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater!!.inflate(R.layout.fragment_home, container, false)
        val tvUserName: TextView = rootView.findViewById(R.id.tvName);
        val tvUserLvl: TextView = rootView.findViewById(R.id.tvLvl);
        val tvUserExp: TextView = rootView.findViewById(R.id.tvExp);
        val tvMoney : TextView = rootView.findViewById(R.id.tvMoney)
        tvUserName.text = Managers.UserManager.user!!.name
        tvUserLvl.text = Managers.UserManager.user!!.lvl.toString()
        tvUserExp.text = Managers.UserManager.user!!.exp.toString()
        tvMoney.text = Managers.UserManager.user!!.money.toString()

        return rootView
    }

    companion object {
        fun newInstance(user: User): FragmentHome {
            val fragment = FragmentHome()
            val args = Bundle()
            args.putSerializable(INTENT_HOME_ID_USER,user)
            fragment.arguments = args

            return fragment
        }
    }
}