package extension.domain.androidreddit.data.network


import androidx.paging.ExperimentalPagingApi
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import extension.domain.androidreddit.data.models.ApiResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.*

@ExperimentalPagingApi
interface APIService{
    @GET("{sub}/hot.json")
    suspend fun paginated(@Path("sub") id: String = "Android", @Query("after") after: String?): ApiResponse


    companion object{
        operator fun invoke() : APIService{
            val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.mmmSSS'Z'")
                .registerTypeAdapter(Date::class.java, DateDeserializer())
                .excludeFieldsWithoutExposeAnnotation()
                .create()

            return Retrofit.Builder()
                .baseUrl("https://www.reddit.com/r/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(Worker.client)
                .build()
                .create(APIService::class.java)
        }
    }
}