package extension.domain.androidreddit.activities.main


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import extension.domain.androidreddit.App
import extension.domain.androidreddit.R
import extension.domain.androidreddit.data.models.ApiResponseDataChildrenData
import extension.domain.androidreddit.extensions.bold
import kotlinx.android.synthetic.main.data_item.view.*

@ExperimentalPagingApi
class PaginatedAdapter(val onItemSelected: ((ApiResponseDataChildrenData?) -> Unit)? = null) : PagingDataAdapter<ApiResponseDataChildrenData, PaginatedAdapter.ViewHolder>(
    COMPARATOR
) {
    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<ApiResponseDataChildrenData>() {
            override fun areItemsTheSame(oldItem: ApiResponseDataChildrenData, newItem: ApiResponseDataChildrenData) =
                oldItem.created == newItem.created

            override fun areContentsTheSame(oldItem: ApiResponseDataChildrenData, newItem: ApiResponseDataChildrenData) =
                oldItem == newItem
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.data_item, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBindView(data: ApiResponseDataChildrenData, position: Int) {
            itemView.apply {
                data.let {
                    title_name.text = it.title

                    author_name.text = String.format(App.AppContext.getString(R.string.by_author), it.author_fullname).bold(String.format(App.AppContext.getString(R.string.by_author), "").length)
                    data_main_layout.setOnClickListener { _->
                        onItemSelected?.invoke(it)
                    }
                }
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBindView(it, position)
        }
    }
}