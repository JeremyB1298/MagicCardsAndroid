package Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CardDB (@SerializedName("id") @Expose var id: Int? = null,
              @SerializedName("cardId") @Expose var cardId: String?= null,
              @SerializedName("cardName") @Expose var cardName: String? = null) : Serializable