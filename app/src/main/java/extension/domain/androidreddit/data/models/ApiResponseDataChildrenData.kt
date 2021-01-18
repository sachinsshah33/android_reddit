package extension.domain.androidreddit.data.models


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*
import kotlin.collections.ArrayList

@Parcelize
@Entity(tableName = "apiResponseDataChildrenData")
data class ApiResponseDataChildrenData(
    @SerializedName("approved_at_utc")
    @Expose
    var approved_at_utc: String? = "",

    @SerializedName("subreddit")
    @Expose
    var subreddit: String? = "",

    @SerializedName("selftext")
    @Expose
    var selftext: String? = "",

    @PrimaryKey
    @SerializedName("created")
    @Expose
    var created: Long,

    @SerializedName("author_fullname")
    @Expose
    var author_fullname: String? = "",

    @SerializedName("url")
    @Expose
    var url: String? = ""
) : Parcelable {

}