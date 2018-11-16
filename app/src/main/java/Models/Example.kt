package Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Example {

    @SerializedName("card")
    @Expose
    var card: Card? = null

}
