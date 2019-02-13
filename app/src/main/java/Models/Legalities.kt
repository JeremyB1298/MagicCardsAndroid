package Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Legalities {

    @SerializedName("standard")
    @Expose
    var standard: String? = null
    @SerializedName("future")
    @Expose
    var future: String? = null
    @SerializedName("frontier")
    @Expose
    var frontier: String? = null
    @SerializedName("modern")
    @Expose
    var modern: String? = null
    @SerializedName("legacy")
    @Expose
    var legacy: String? = null
    @SerializedName("pauper")
    @Expose
    var pauper: String? = null
    @SerializedName("vintage")
    @Expose
    var vintage: String? = null
    @SerializedName("penny")
    @Expose
    var penny: String? = null
    @SerializedName("commander")
    @Expose
    var commander: String? = null
    @SerializedName("duel")
    @Expose
    var duel: String? = null
    @SerializedName("oldschool")
    @Expose
    var oldschool: String? = null

}