package test.org.kastatest.ui.campaigns

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.widget.Toast

import butterknife.BindView
import butterknife.ButterKnife
import test.org.kastatest.R
import test.org.kastatest.data.entities.Campaign

class CampaignsView @JvmOverloads constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {

    @BindView(R.id.campaigns_list)
    @JvmField
    var mCampaignsList: RecyclerView? = null

    private lateinit var mViewModel: CampaignsViewModel
    private val mAdapter = CampaignsAdapter()

    fun setup(viewModel: CampaignsViewModel, lifecycleOwner: LifecycleOwner) {
        mViewModel = viewModel
        mViewModel.campaigns.observe(lifecycleOwner, Observer { workWithCampaigns(it) })
        mViewModel.showError.observe(lifecycleOwner, Observer { workWithError(it) })
    }

    private fun workWithCampaigns(campaigns: List<Campaign>?) {
        mAdapter.replaceWith(campaigns)
    }

    private fun workWithError(error: Boolean?) {
        error?.let {
            if (error) {
                Toast.makeText(context, R.string.internet_error, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        ButterKnife.bind(this)

        mCampaignsList!!.adapter = mAdapter
        mCampaignsList!!.setHasFixedSize(true)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
    }

}
