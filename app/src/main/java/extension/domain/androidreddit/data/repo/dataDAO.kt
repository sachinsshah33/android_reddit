package extension.domain.androidreddit.data.repo

import androidx.paging.PagingSource
import androidx.room.*
import extension.domain.androidreddit.data.models.ApiResponseDataChildrenData


@Dao
interface dataDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(models: List<ApiResponseDataChildrenData>)
    @Update
    fun update(models: List<ApiResponseDataChildrenData>)
    @Delete
    fun delete(models: List<ApiResponseDataChildrenData>)

    @Query("SELECT * FROM apiResponseDataChildrenData")
    fun getDataFromLocalPaginated(): PagingSource<Int, ApiResponseDataChildrenData>
}
