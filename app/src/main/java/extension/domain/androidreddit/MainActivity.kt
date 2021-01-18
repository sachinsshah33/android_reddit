package extension.domain.androidreddit

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import extension.domain.androidreddit.data.models.ApiResponseDataChildrenData
import extension.domain.androidreddit.data.repo.DataViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch


@ExperimentalPagingApi
class MainActivity : AppCompatActivity(), ((ApiResponseDataChildrenData?) -> Unit) {
    val viewModel by lazy {
        ViewModelProvider(this).get(DataViewModel::class.java)
    }

    var paginatedAdapter: PaginatedAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        paginatedAdapter = PaginatedAdapter(this)
        data_recycler_view?.apply {
            addItemDecoration(
                DividerItemDecorator(
                    ContextCompat.getDrawable(
                        this@MainActivity,
                        R.drawable.divider
                    )!!
                )
            )
            adapter = paginatedAdapter
        }


        fetch()
    }


    @SuppressLint("CheckResult")
    private fun fetch() {
        showTapToRetry(false)
        viewModel.getDataFromCloudPaginated().subscribe {
            lifecycleScope.launch {
                paginatedAdapter?.submitData(it)
            }
        }
    }

    fun showTapToRetry(boolean: Boolean){

    }

    override fun invoke(item: ApiResponseDataChildrenData?) {
        item?.url?.let {
            CustomTabsIntent.Builder().build().apply {
                launchUrl(this@MainActivity, Uri.parse(it))
            }
        }
    }
}