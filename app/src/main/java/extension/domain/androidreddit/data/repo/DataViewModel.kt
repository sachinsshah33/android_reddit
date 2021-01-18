package extension.domain.androidreddit.data.repo


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.rxjava2.cachedIn
import extension.domain.androidreddit.data.models.ApiResponseDataChildren
import extension.domain.androidreddit.data.models.ApiResponseDataChildrenData
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@ExperimentalPagingApi
class DataViewModel(application: Application) : AndroidViewModel(application) {
    var repo: dataRepo? = null
    init {
        repo = dataRepo()
    }

    fun getDataFromLocalPaginatedFlow(): Flow<PagingData<ApiResponseDataChildrenData>> {
        return repo!!.getDataFromLocalPaginated().cachedIn(viewModelScope).distinctUntilChanged()
    }

    fun getDataFromCloudPaginated(): Observable<PagingData<ApiResponseDataChildrenData>> {
        return repo!!.getDataFromCloudPaginated().cachedIn(viewModelScope).distinctUntilChanged()
    }
}