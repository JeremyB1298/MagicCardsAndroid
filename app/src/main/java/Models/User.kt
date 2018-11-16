package Models

import java.util.*

class User(val id: Int, var fbId: String?, var googleId: String?, var name: String?, var email: String?, private val isNew: Boolean?) {
    var listCard: ArrayList<Card>? = null
}
