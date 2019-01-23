package Models

import java.io.Serializable
import java.util.*

class User(var id: Int, var fbId: String? = null, var googleId: String?= null,
           var name: String?= null, var isNew: Boolean?= null, var lvl: Int?= null,
           var exp: Int?= null) : Serializable