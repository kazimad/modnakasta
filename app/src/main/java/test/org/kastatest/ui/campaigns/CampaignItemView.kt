package test.org.kastatest.ui.campaigns

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide

import butterknife.BindView
import butterknife.ButterKnife
import test.org.kastatest.data.entities.Campaign
import test.org.kastatest.R

class CampaignItemView @JvmOverloads constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {

    @BindView(R.id.campaign_name)
    internal var textName: TextView? = null

    @BindView(R.id.image_from_net)
    internal var image: ImageView? = null

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var heightMeasureSpec = heightMeasureSpec
        val width = View.MeasureSpec.getSize(widthMeasureSpec)
        val height = View.MeasureSpec.getSize(heightMeasureSpec)

        val heightAspectRatio = (width * ASPECT_RATIO).toInt()
        if (width != 0 && height != heightAspectRatio) {
            setMeasuredDimension(width, heightAspectRatio)
            heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(heightAspectRatio, View.MeasureSpec.EXACTLY)
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        ButterKnife.bind(this)
    }

    fun bindTo(campaign: Campaign, position: Int) {
        textName!!.text = campaign.name
        if (TextUtils.isEmpty(campaign.mImageUrl)) {
            image!!.setImageDrawable(null)
        } else if (position == 1) {
            Glide.with(context)
                    .load(campaign.mImageUrl)
                    .into(image!!)
        } else {
            Glide.with(context)
                    .load("https://m.cdnmk.net/imgw/loc/360x198/" + campaign.mImageUrl)
                    .into(image!!)
        }
    }

    companion object {

        private val ASPECT_RATIO = 0.55f
    }

}
