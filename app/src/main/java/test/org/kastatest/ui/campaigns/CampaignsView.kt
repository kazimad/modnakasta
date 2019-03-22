package test.org.kastatest.ui.campaigns

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.widget.Toast

import butterknife.BindView
import butterknife.ButterKnife
import test.org.kastatest.R

class CampaignsView @JvmOverloads constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {

    @BindView(R.id.campaigns_list)
    internal var mCampaignsList: RecyclerView? = null

    private lateinit var mViewModel: CampaignsViewModel
    private val mAdapter = CampaignsAdapter()

    fun setup(viewModel: CampaignsViewModel, lifecycleOwner: LifecycleOwner) {
        mViewModel = viewModel
        mViewModel.campaigns.observe(lifecycleOwner, { campaigns -> mAdapter.replaceWith(campaigns) })
        mViewModel.showError.observe(lifecycleOwner, { error ->
            if (error!!) {
                val toast = Toast(context)
                toast.setText(R.string.internet_error)
                toast.show()
            }
        })
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
