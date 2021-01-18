package extension.domain.androidreddit.data.redditData


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagedList
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.rxjava2.cachedIn
import extension.domain.androidreddit.data.models.ApiResponseDataChildrenData
import io.reactivex.Flowable
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@ExperimentalPagingApi
class DataViewModel(application: Application) : AndroidViewModel(application) {
    var repo: DataRepo? = null
    init {
        repo = DataRepo()
    }

    fun getDataFromLocalPaginatedFlow(): Observable<PagingData<ApiResponseDataChildrenData>> {
        return repo!!.getDataFromLocalPaginated().cachedIn(viewModelScope).distinctUntilChanged()
    }

    /*fun getDataFromLocalPaginatedFlowable(): Flowable<PagedList<ApiResponseDataChildrenData>> {
        return repo!!.getDataFromLocalPaginated().cachedIn(viewModelScope).distinctUntilChanged()
    }*/

    fun getDataFromCloudPaginated(): Observable<PagingData<ApiResponseDataChildrenData>> {
        return repo!!.getDataFromCloudPaginated().cachedIn(viewModelScope).distinctUntilChanged()
    }
}