package Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

class User(@SerializedName("id") @Expose var id: Int? = null,
           @SerializedName("googleId") @Expose var googleId: String?= null,
           @SerializedName("fbId") @Expose var fbId: String? = null,
           @SerializedName("name") @Expose var name: String?= null,
           @SerializedName("isNew") @Expose var isNew: Boolean?= null,
           @SerializedName("lvl") @Expose var lvl: Int?= null,
           @SerializedName("exp") @Expose var exp: Int?= null) : Serializable