package Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Deck : Serializable{
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("userId")
    @Expose
    var userId: Int? = null
    @SerializedName("cards")
    @Expose
    var cards: ArrayList<DeckCard>? = ArrayList()

}