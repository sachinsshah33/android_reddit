package extension.domain.androidreddit.data.network


import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import extension.domain.androidreddit.App
import extension.domain.androidreddit.Constants
import extension.domain.androidreddit.MainActivity
import extension.domain.androidreddit.data.models.ApiResponseDataChildrenData
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class ApiPagingSource : PagingSource<String, ApiResponseDataChildrenData>() {
    override suspend fun load(params: LoadParams<String>): LoadResult<String, ApiResponseDataChildrenData> {
        val page = params.key
        return try {
            val response = APIService.invoke().paginated(after = page)
            LoadResult.Page(
                response.data!!.children.map { it.data!! }, prevKey = page,
                nextKey = response.data?.after
            )
        } catch (exception: IOException) {
            (App.getCurrentActivity as? MainActivity)?.showTapToRetry(true)
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            (App.getCurrentActivity as? MainActivity)?.showTapToRetry(true)
            return LoadResult.Error(exception)
        }
    }
}