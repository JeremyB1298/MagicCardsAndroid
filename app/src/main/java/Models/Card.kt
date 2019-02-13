package Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Card : Serializable {

    @SerializedName("object")
    @Expose
    var `object`: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("oracle_id")
    @Expose
    var oracleId: String? = null
    @SerializedName("multiverse_ids")
    @Expose
    var multiverseIds: List<Int>? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("printed_name")
    @Expose
    var printedName: String? = null
    @SerializedName("lang")
    @Expose
    var lang: String? = null
    @SerializedName("released_at")
    @Expose
    var releasedAt: String? = null
    @SerializedName("uri")
    @Expose
    var uri: String? = null
    @SerializedName("scryfall_uri")
    @Expose
    var scryfallUri: String? = null
    @SerializedName("layout")
    @Expose
    var layout: String? = null
    @SerializedName("highres_image")
    @Expose
    var highresImage: Boolean? = null
    @SerializedName("image_uris")
    @Expose
    var imageUris: ImageUris? = null
    @SerializedName("mana_cost")
    @Expose
    var manaCost: String? = null
    @SerializedName("cmc")
    @Expose
    var cmc: Int? = null
    @SerializedName("type_line")
    @Expose
    var typeLine: String? = null
    @SerializedName("printed_type_line")
    @Expose
    var printedTypeLine: String? = null
    @SerializedName("oracle_text")
    @Expose
    var oracleText: String? = null
    @SerializedName("printed_text")
    @Expose
    var printedText: String? = null
    @SerializedName("colors")
    @Expose
    var colors: List<String>? = null
    @SerializedName("color_identity")
    @Expose
    var colorIdentity: List<String>? = null
    @SerializedName("legalities")
    @Expose
    var legalities: Legalities? = null
    @SerializedName("games")
    @Expose
    var games: List<String>? = null
    @SerializedName("reserved")
    @Expose
    var reserved: Boolean? = null
    @SerializedName("foil")
    @Expose
    var foil: Boolean? = null
    @SerializedName("nonfoil")
    @Expose
    var nonfoil: Boolean? = null
    @SerializedName("oversized")
    @Expose
    var oversized: Boolean? = null
    @SerializedName("promo")
    @Expose
    var promo: Boolean? = null
    @SerializedName("reprint")
    @Expose
    var reprint: Boolean? = null
    @SerializedName("set")
    @Expose
    var set: String? = null
    @SerializedName("set_name")
    @Expose
    var setName: String? = null
    @SerializedName("set_uri")
    @Expose
    var setUri: String? = null
    @SerializedName("set_search_uri")
    @Expose
    var setSearchUri: String? = null
    @SerializedName("scryfall_set_uri")
    @Expose
    var scryfallSetUri: String? = null
    @SerializedName("rulings_uri")
    @Expose
    var rulingsUri: String? = null
    @SerializedName("prints_search_uri")
    @Expose
    var printsSearchUri: String? = null
    @SerializedName("collector_number")
    @Expose
    var collectorNumber: String? = null
    @SerializedName("digital")
    @Expose
    var digital: Boolean? = null
    @SerializedName("rarity")
    @Expose
    var rarity: String? = null
    @SerializedName("watermark")
    @Expose
    var watermark: String? = null
    @SerializedName("illustration_id")
    @Expose
    var illustrationId: String? = null
    @SerializedName("artist")
    @Expose
    var artist: String? = null
    @SerializedName("border_color")
    @Expose
    var borderColor: String? = null
    @SerializedName("frame")
    @Expose
    var frame: String? = null
    @SerializedName("frame_effect")
    @Expose
    var frameEffect: String? = null
    @SerializedName("full_art")
    @Expose
    var fullArt: Boolean? = null
    @SerializedName("story_spotlight")
    @Expose
    var storySpotlight: Boolean? = null
    @SerializedName("edhrec_rank")
    @Expose
    var edhrecRank: Int? = null
    @SerializedName("prices")
    @Expose
    var prices: Prices? = null
    @SerializedName("related_uris")
    @Expose
    var relatedUris: RelatedUris? = null
    @SerializedName("purchase_uris")
    @Expose
    var purchaseUris: PurchaseUris? = null

}