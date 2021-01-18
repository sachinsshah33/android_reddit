package extension.domain.androidreddit.data.redditData


import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import extension.domain.androidreddit.App
import extension.domain.androidreddit.activities.main.MainActivity
import extension.domain.androidreddit.data.models.ApiResponseDataChildrenData
import extension.domain.androidreddit.data.network.APIService
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class DataPagingSource : PagingSource<String, ApiResponseDataChildrenData>() {
    override suspend fun load(params: LoadParams<String>): LoadResult<String, ApiResponseDataChildrenData> {
        val page = params.key
        return try {
            val response = APIService.invoke().paginated(after = page)
            val data = response.data!!.children.map { it.data!! }
            DataRepo().insert(models = data)
            LoadResult.Page(data, prevKey = page, nextKey = response.data?.after)
        } catch (exception: IOException) {
            (App.getCurrentActivity as? MainActivity)?.showTapToRetry(true)
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            (App.getCurrentActivity as? MainActivity)?.showTapToRetry(true)
            return LoadResult.Error(exception)
        }
    }
}