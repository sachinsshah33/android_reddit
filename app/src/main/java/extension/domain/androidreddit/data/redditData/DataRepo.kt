package extension.domain.androidreddit.data.redditData


import androidx.paging.*
import androidx.paging.rxjava2.observable
import extension.domain.androidreddit.App
import extension.domain.androidreddit.utils.Constants
import extension.domain.androidreddit.data.local.AppDatabase
import extension.domain.androidreddit.data.models.ApiResponseDataChildrenData
import io.reactivex.Flowable
import io.reactivex.Observable

@ExperimentalPagingApi
class DataRepo() {
    private var dataDao: DataDAO? = null

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
    fun getDataFromLocalPaginated(): Observable<PagingData<ApiResponseDataChildrenData>> {
        return Pager(config = PagingConfig(pageSize = Constants.DEFAULT_PAGE_SIZE, enablePlaceholders = false), pagingSourceFactory = { getDataFromLocalPaginatedFlow() }).observable
    }


    fun getDataFromLocalPaginatedFlowable(): Flowable<PagedList<ApiResponseDataChildrenData>> {
        return dataDao!!.getDataFromLocalPaginatedFlowable().toFlowable(pageSize = Constants.DEFAULT_PAGE_SIZE)
    }


    fun getDataFromCloudPaginated(pagingConfig: PagingConfig = PagingConfig(pageSize = Constants.DEFAULT_PAGE_SIZE, enablePlaceholders = true)): Observable<PagingData<ApiResponseDataChildrenData>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { DataPagingSource() }
        ).observable
    }
}