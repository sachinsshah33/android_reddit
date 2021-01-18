package extension.domain.androidreddit.data.models


import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*
import kotlin.collections.ArrayList

@Parcelize
data class ApiResponseData(
    @SerializedName("modhash")
    @Expose
    var modhash: String? = "",

    @SerializedName("dist")
    @Expose
    var dist: Int? = 0,

    @SerializedName("children")
    @Expose
    var children: ArrayList<ApiResponseDataChildren> = ArrayList(),

    @SerializedName("after")
    @Expose
    var after: String? = "",

    @SerializedName("before")
    @Expose
    var before: String? = ""
) : Parcelable {

}