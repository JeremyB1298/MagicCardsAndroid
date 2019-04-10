package Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lpiem.magiccards.R



class RcyclViewPowerIndFragment : androidx.fragment.app.Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bundle = this.arguments
        var rarety = bundle!!.getString("Rarety")


        val layout = when (rarety) {
            "common" -> R.layout.common_rarety_indicator
            "uncommon" -> R.layout.uncommon_rarety_indicator
            "rare" -> R.layout.rare_rarety_indicator
            "mythicrare" -> R.layout.mythicrare_rarety_indicator

            else -> { // Note the block
                R.layout.common_rarety_indicator
            }

        }

        var rootView = inflater!!.inflate(layout, container, false)

        return rootView
    }

    companion object {
        fun newInstance(rarety: String): Fragment_home {
            val fragment = Fragment_home()
            val args = Bundle()
            args.putString("Rarety",rarety)
            fragment.arguments = args

            return fragment
        }
    }
}



