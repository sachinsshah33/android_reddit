package extension.domain.androidreddit.data.repo


import androidx.paging.*
import androidx.paging.rxjava2.observable
import extension.domain.androidreddit.App
import extension.domain.androidreddit.Constants
import extension.domain.androidreddit.data.local.AppDatabase
import extension.domain.androidreddit.data.models.ApiResponseDataChildrenData
import extension.domain.androidreddit.data.network.ApiPagingSource
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class dataRepo() {
    private var dataDao: dataDAO? = null

    init {
        val myDatabase =
            AppDatabase.getInstance(App.AppContext)
        dataDao = myDatabase.dataDAO()

    }


    fun insert(model: ApiResponseDataChildrenData?=null, models: List<ApiResponseDataChildrenData>?=null){
        ArrayList<ApiResponseDataChildrenData>().apply {
            model?.let {
                add(it)
            }
            models?.let {
                addAll(it)
            }
            if(!isNullOrEmpty()){
                dataDao?.insert(this)
            }
        }
    }
    fun getDataFromLocalPaginatedFlow(): PagingSource<Int, ApiResponseDataChildrenData> {
        return dataDao!!.getDataFromLocalPaginated()
    }
    fun getDataFromLocalPaginated(): Flow<PagingData<ApiResponseDataChildrenData>> {
        return Pager(config = PagingConfig(pageSize = Constants.DEFAULT_PAGE_SIZE, enablePlaceholders = false), pagingSourceFactory = { getDataFromLocalPaginatedFlow() }).flow
    }


    fun getDataFromCloudPaginated(pagingConfig: PagingConfig = PagingConfig(pageSize = Constants.DEFAULT_PAGE_SIZE, enablePlaceholders = true)): Observable<PagingData<ApiResponseDataChildrenData>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { ApiPagingSource() }
        ).observable
    }
}