package Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DeckCard : Serializable {
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("cardId")
    @Expose
    var cardId: String? = null
    @SerializedName("deckId")
    @Expose
    var deckId: Int? = null
}