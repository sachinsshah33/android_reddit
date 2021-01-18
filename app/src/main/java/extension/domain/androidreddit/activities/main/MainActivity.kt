package extension.domain.androidreddit.activities.main

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import extension.domain.androidreddit.R
import extension.domain.androidreddit.data.models.ApiResponseDataChildrenData
import extension.domain.androidreddit.data.redditData.DataViewModel
import extension.domain.androidreddit.extensions.showSnackbar
import extension.domain.androidreddit.utils.DividerItemDecorator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch


@ExperimentalPagingApi
class MainActivity : AppCompatActivity(), ((ApiResponseDataChildrenData?) -> Unit) {
    private val viewModel by lazy {
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

        try_again_fab?.setOnClickListener {
            fetchFromNetwork()
        }

        fetchFromNetwork()
    }


    @SuppressLint("CheckResult")
    private fun fetchFromNetwork() {
        showTapToRetry(false)
        viewModel.getDataFromCloudPaginated().subscribe {
            lifecycleScope.launch {
                paginatedAdapter?.submitData(it)
            }
        }
    }

    @SuppressLint("CheckResult")
    private fun fetchFromCache() {
        viewModel.getDataFromLocalPaginatedFlow().subscribe {
            lifecycleScope.launch {
                paginatedAdapter?.submitData(it)
            }
        }
    }

    val handler = Handler(Looper.getMainLooper())
    fun showTapToRetry(show: Boolean){
        handler.removeCallbacksAndMessages(null)
        handler.postDelayed({
            try_again_fab?.isVisible = show
            if(show){
                fetchFromCache()
                showSnackbar(getString(R.string.try_again_toast))
                //Toast.makeText(App.AppContext, getString(R.string.try_again_toast), Toast.LENGTH_LONG).show()
            }
        }, 500)
        //added this hacky delay to stop FloatingActionButton from quickly hiding and showing, if it fails consecutively
    }

    override fun invoke(item: ApiResponseDataChildrenData?) {
        item?.url?.let {
            CustomTabsIntent.Builder().build().apply {
                launchUrl(this@MainActivity, Uri.parse(it))
            }
        }
    }
}