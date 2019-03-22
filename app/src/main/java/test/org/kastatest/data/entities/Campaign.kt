package test.org.kastatest.data.entities

import com.google.gson.annotations.SerializedName

import org.parceler.Parcel

import java.util.Date

@Parcel
class Campaign {

    var id = 0
    var name: String? = null
    var description: String? = null

    @SerializedName("now_image")
    var mImageUrl = ""

    @SerializedName("starts_at")
    var startsAt: Date? = null

}
