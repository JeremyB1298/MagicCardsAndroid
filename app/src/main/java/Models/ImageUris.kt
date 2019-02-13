package Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class ImageUris {

    @SerializedName("small")
    @Expose
    var small: String? = null
    @SerializedName("normal")
    @Expose
    var normal: String? = null
    @SerializedName("large")
    @Expose
    var large: String? = null
    @SerializedName("png")
    @Expose
    var png: String? = null
    @SerializedName("art_crop")
    @Expose
    var artCrop: String? = null
    @SerializedName("border_crop")
    @Expose
    var borderCrop: String? = null

}