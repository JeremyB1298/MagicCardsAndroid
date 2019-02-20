package Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class PurchaseUris {

    @SerializedName("tcgplayer")
    @Expose
    var tcgplayer: String? = null
    @SerializedName("cardmarket")
    @Expose
    var cardmarket: String? = null
    @SerializedName("cardhoarder")
    @Expose
    var cardhoarder: String? = null

}