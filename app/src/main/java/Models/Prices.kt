package Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Prices {

    @SerializedName("usd")
    @Expose
    var usd: Any? = null
    @SerializedName("usd_foil")
    @Expose
    var usdFoil: Any? = null
    @SerializedName("eur")
    @Expose
    var eur: Any? = null
    @SerializedName("tix")
    @Expose
    var tix: Any? = null

}