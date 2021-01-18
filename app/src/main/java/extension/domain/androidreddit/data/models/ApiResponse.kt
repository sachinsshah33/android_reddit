package extension.domain.androidreddit.data.models


import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlin.collections.ArrayList

@Parcelize
data class ApiResponse(
    @SerializedName("kind")
    @Expose
    var kind: String? = "",

    @SerializedName("data")
    @Expose
    var data: ApiResponseData? = null
) : Parcelable {

}