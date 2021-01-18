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
    @SerializedName("created_utc")
    @Expose
    var created_utc: Long? = 0,

    @SerializedName("subreddit")
    @Expose
    var subreddit: String? = "",

    @SerializedName("selftext")
    @Expose
    var selftext: String? = "",

    @SerializedName("title")
    @Expose
    var title: String? = "",

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
) : Parcelable