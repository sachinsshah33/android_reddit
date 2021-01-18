package extension.domain.androidreddit.data.models


import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*
import kotlin.collections.ArrayList

@Parcelize
data class ApiResponseDataChildren(
    @SerializedName("kind")
    @Expose
    var kind: String? = "",

    @SerializedName("data")
    @Expose
    var data: ApiResponseDataChildrenData? = null
) : Parcelable