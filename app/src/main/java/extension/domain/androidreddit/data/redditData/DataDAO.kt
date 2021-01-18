package extension.domain.androidreddit.data.redditData

import androidx.paging.DataSource
import androidx.paging.PagingSource
import androidx.room.*
import extension.domain.androidreddit.data.models.ApiResponseDataChildrenData


@Dao
interface DataDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(models: List<ApiResponseDataChildrenData>)
    @Update
    fun update(models: List<ApiResponseDataChildrenData>)
    @Delete
    fun delete(models: List<ApiResponseDataChildrenData>)

    @Query("SELECT * FROM apiResponseDataChildrenData")
    fun getDataFromLocalPaginated(): PagingSource<Int, ApiResponseDataChildrenData>

    @Query("SELECT * FROM apiResponseDataChildrenData")
    fun getDataFromLocalPaginatedFlowable(): DataSource.Factory<Int, ApiResponseDataChildrenData>
}
