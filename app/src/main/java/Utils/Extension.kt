package Utils


import androidx.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attchToRoot: Boolean = false): View {

    return LayoutInflater.from(context).inflate(layoutRes, this, attchToRoot)
}