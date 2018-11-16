package Models

import java.util.*

class User(var id: Int, var fbId: String? = null, var googleId: String?= null,
           var name: String?= null, var email: String?= null, var isNew: Boolean?= null)