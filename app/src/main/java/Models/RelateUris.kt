package Models


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RelatedUris {

    @SerializedName("gatherer")
    @Expose
    var gatherer: String? = null
    @SerializedName("tcgplayer_decks")
    @Expose
    var tcgplayerDecks: String? = null
    @SerializedName("edhrec")
    @Expose
    var edhrec: String? = null
    @SerializedName("mtgtop8")
    @Expose
    var mtgtop8: String? = null

}