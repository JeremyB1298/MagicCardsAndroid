package Models

import java.io.Serializable

class Card  (var name: String?,var manaCost: String?,var cmc: Int?,var colors: List<String>? = null,
            var colorIdentify: List<String>? = null, var type: String?,var types:List<String>? = null,
            var rarity: String?,var set: String?,var setName: String?, var text: String?,
            var flavor: String?,var artist: String?,var layout: String?,
            var multiverseid: Int?,var imageUrl: String?, var printings: List<String>? = null,
            var originalText: String?,var originalType: String?,var legalities: List<Legality>? = null,
            var id: String?) : Serializable;

